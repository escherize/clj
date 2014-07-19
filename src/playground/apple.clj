(ns playground.apple
  [:require [clojure.string :as str]
            [print.foo :refer :all]])

(defn word-count [path]
  (->> path
       slurp
       str/split-lines
       (mapcat #(str/split % #"\s+|<|>"))
       frequencies))


(defn rent-finder [{:keys  [room-dimensions total-sq-ft price] :as apt-info}]
  (print-let [room-dimensions  room-dimensions
              total-sq-ft      total-sq-ft 
              price            price
              room-sizes       (for [[w h] room-dimensions] (* w h))
              cost-per-sq-foot (/ price total-sq-ft)
              common-space     (- total-sq-ft (apply + room-sizes))
              common-space-pp  (/ common-space 5)
              area-per-room    [(+ (first room-sizes)  (* 2 common-space-pp))
                                (+ (second room-sizes) common-space-pp)
                                (+ (last room-sizes)   common-space-pp)]
              cost-per-room    (map (partial * cost-per-sq-foot) area-per-room)]
             (map #(format "%.02f" %) cost-per-room)))

(def high-price 3635)
(def low-price 3382)
(def total-sq-ft 1267)
(def room-dimensions [[15.25 11.25] [11.5 13.5] [12.5 9.75]])

(rent-finder {:room-dimensions 
              :total-sq-ft 
              :price})

(comment
  (time (word-count "http://shakespeare.mit.edu/midsummer/full.html"))
)


