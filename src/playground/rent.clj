(ns playground.rent)

(defn rent []
  (let [total-sq-ft  1267
        price        3479
        utilities    275.12
        rooms        [{:title "jeff's room"  :headcount 3 :area 171.5625}
                      {:title "bryan's room" :headcount 1 :area 155.25}  
                      {:title "thaly's room" :headcount 1 :area 121.875}]
        private-area (apply + (map :area rooms))
        occupants    (apply + (map :headcount rooms))
        common-area  (- total-sq-ft private-area)
        price-sqft   (double (/ price total-sq-ft))
        common-price (-> common-area (* price-sqft) (/ occupants))
        common-util  (/ utilities occupants)]
    (map (fn [room]
           (assoc room :total-price-pp (-> (:area room)
                                           (* price-sqft)
                                           (/ (:headcount room))
                                           (+ common-price)
                                           (+ common-util))))
         rooms)))

(rent 1267 3479 275.12)
