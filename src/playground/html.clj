(ns playground.html
  (:require [hiccup.core :as h]
            [hiccup.page :as p]
            [clojure.math.combinatorics :as combo]
            [clojure.string :as str]))

(defonce table
  (->> "/usr/share/dict/words"
       slurp
       .toLowerCase
       str/split-lines
       distinct
       (group-by sort)))

(defn anagrams-of [word]
  (get table (sort word) []))

(defn anagrams? [word1 word2]
  (= (sort word1) (sort word2)))


(defn section [title word value]
  [:div
   [:span (str title " " word)]
   [:br]
   [:span value]
   [:hr]])

(defonce dict
  (->> "/usr/share/dict/words"
       slurp
       .toLowerCase
       str/split-lines
       set))

(defn web-page [word]
  (let [anagrams (anagrams-of word)]
    [:div.container [:h2 word]
     (section "Length of" word (count word))
     (section "Anagram count" word (count anagrams))
     [:div
      [:h2 (str "Anagrams of " word)]
      [:ul
       (map (fn [item] [:li item]) anagrams)]]]))

(spit "/Users/bryanmaass/Desktop/t.html"
      (h/html
       (p/include-css "http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")
       (web-page "stop")))

(defn words-of-length [n]
  (filter #(= n (count %)) dict))

(defn made-of [word lp]
  (every? identity
          (for [[char n] (frequencies word)]
            (let [lp-count (get lp char 0)]
              (<= n lp-count)))))

;; (remove-from-lp {\a 3} "a") ;=> {/a 2}
(defn remove-from-lp [lp word]
  (->> word
       frequencies
       (merge-with - lp)
       (remove (fn [[k v]] (= v 0)))
       (into {})))

(defn add-to-lp [lp word]
  (->> (frequencies word)
       (merge-with + lp)
       (into {})))

(defn lp+len->words+lp
  ([lp len]
     (lp+len->words+lp lp len []))
  ([lp len words]
     (remove nil? (for [cand (words-of-length len)]
                    (when (made-of cand lp)
                      [(conj words cand) (remove-from-lp lp cand)])))))

(defn words+lps->more-words+new-lps [words+lps next-len]
  (for [[words lp] words+lps]
    (lp+len->words+lp lp next-len words)))

#_(defn foo [in]
    (let [lens (map count in)
          wp   (frequencies (apply str in))]
      (loop [p        wp
             sentance []
             length   0]
        (if (= length lens)
          (prn sentance)
          (for [word (words-of-length (get lens length))]
            (if (made-of word p)
              (foo (remove-from-lp p word) (conj sentance word) (inc length))
              (foo (add-to-lp p word) (pop sentance) (dec length))))))))


(comment
  (def in ["this" "is" "a" "name"])
  (foo in)
  )

