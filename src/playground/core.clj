(ns playground.core)

(defn coin-change [cents]
  (cond
   (> 0 cents) 0
   (zero? cents) 1
   :else
   (+ (coin-change (- cents 25))
      (coin-change (- cents 10))
      (coin-change (- cents 5))
      (coin-change (- cents 1)))))



(defn sicp-one-three [n1 n2 n3]
  (let [nums (rest (sort [n1 n2 n3]))]
    (apply + (map #(* % %) nums))))

(sicp-one-three 1 2 3)



