(ns playground.apple)

(use 'clojure.string)

(->> (slurp "http://shakespeare.mit.edu/midsummer/full.html")
     split-lines
     (map #(split % #"\s+|<|>") )
     flatten
     frequencies
     seq
     (sort-by second))



