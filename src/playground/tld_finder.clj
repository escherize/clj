(ns playground.tld-finder
  (:require [net.cgrand.enlive-html :as html]))

(def ^:dynamic *base-url* "http://en.wikipedia.org/wiki/List_of_Internet_top-level_domains")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(defn td>a-s []
  (map html/text
       (html/select
        (fetch-url *base-url*) [:td :> :a])))

(defn tldz []
  (->> (td>a-s)
       (filter  #(.startsWith % "."))
       (map #(clojure.string/replace % #"\." ""))))

(defn dictionary []
  (-> (slurp "/usr/share/dict/words")
      (clojure.string/split-lines)))

(defn words-that-end-with [ending dict]
  (filter #(.endsWith % ending) dict))

(defn fill-in-tlds [endings dict]
  (for [ending endings]
    (let [hits (->> (words-that-end-with ending dict)
                    (sort-by count)
                    (clojure.string/join "\n"))
          file-name (str "/Users/bryanmaass/Desktop/tlds/tlds-for-" ending ".txt")]
      (spit file-name hits))))

(comment

  (def tld-names (tldz))
  (def dict (dictionary))
  (fill-in-tlds tld-names dict)

  )
