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

































































































(def cell
  {:o? false
   :b? true})

(defn make-2d-array [x y cell]
  (repeatedly y (fn [] (repeatedly x (fn [] cell)))))

(defn bomb-spots 
  ([n x y] (bomb-spots n x y #{}))
  ([n x y spots]
     (if (= n (count spots))
       spots
       (bomb-spots n x y 
                   (conj spots [(rand-int x) (rand-int y)])))))

(defn board [x y num-bombs]
  (let [empty-board (make-2d-array x y cell)
        locations (bomb-spots num-bombs x y)]
    locations))

(board 3 2 3)

