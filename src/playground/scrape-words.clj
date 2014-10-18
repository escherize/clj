(ns playground.scrape-words
  (:require [net.cgrand.enlive-html :as html]
            [clojure.string :as str]
            [clj-http.client :as client]))


(def ^:dynamic *url* "http://www.englishclub.com/vocabulary/common-words-5000.htm")


(comment
  (defonce page (fetch-url *url*))

  (def words
    (->> [:li]
         (html/select page)
         (mapcat :content)
         (str/join "\n")))

  (spit "words.txt" words)


  )

(defn words []
  (clojure.string/split-lines
   (slurp "/usr/share/dict/words")))

(def valid-word-set
  (->> (words)
       set))

(time (map valid-word-set ))
