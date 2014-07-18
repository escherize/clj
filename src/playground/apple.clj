(ns playground.apple
  [:require [clojure.string :as str]])


(defn word-count [path]
  (->> (slurp )
       split-lines
       (mapcat #(split % #"\s+|<|>") )
       frequencies
       (sort-by second)))

(comment
  (word-count "http://shakespeare.mit.edu/midsummer/full.html")
)

