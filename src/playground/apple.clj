(ns playground.apple
  (:require [clojure.string :as str]
            [net.cgrand.enlive-html :as html]))

(def *url* "http://shakespeare.mit.edu/midsummer/full.html")

(comment
  (word-count "http://shakespeare.mit.edu/midsummer/full.html")
  )
