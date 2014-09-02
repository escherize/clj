(ns playground.rent
  [:require [clojure.string :as str]
   [print.foo :refer :all]
   [clojure.pprint :refer :all]])

(defn sum [nums]
  "takes a list of nums and finds the sum"
  (apply + nums))

(defn add-area [room]
  (assoc room :area (apply * (:dimensions room))))

(defn rent []
  (let [total-sq-ft     1267
        price           3479
        utilities       275.12
        rooms           (->> [{:title "jeff's room"  :headcount 3 :dimensions [15.25 11.25]}
                              {:title "bryan's room" :headcount 1 :dimensions [11.5   13.5]}
                              {:title "thaly's room" :headcount 1 :dimensions [12.5   9.75]}]
                             (map add-area))
        private-area    (sum (map :area rooms))
        occupants       (sum (map :headcount rooms))
        common-area     (- total-sq-ft private-area)
        price-sqft      (double (/ price total-sq-ft))
        common-price    (-> common-area (* price-sqft) (/ occupants))
        common-util     (/ utilities occupants)]
    (->> rooms
         (map (fn [room]
                assoc room :total-price-pp (-> (:area room)
                                               (* price-sqft)
                                               (/ (:headcount room))
                                               (+ common-price)
                                               (+ common-util)))))))
