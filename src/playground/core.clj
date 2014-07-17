(ns playground.core)

(defn steps [n]
  (cond
   (> 0 n)   0
   (zero? n) 1
   :else     (+ (steps (- n 2))
                (steps (- n 1)))))

(defn coin-change [cents]
  (cond
   (> 0 cents)   0
   (zero? cents) 1
   :else        (+ (coin-change (- cents 25))
                   (coin-change (- cents 10))
                   (coin-change (- cents 5))
                   (coin-change (- cents 1)))))


(defn ks+v-to-multiple-entries [[keys val]]
  (into {} (for [k keys] [k val])))

(defn set-keys-to-many-entries [mm]
  (let [kvs (seq mm)]
    (apply merge (map ks+v-to-multiple-entries kvs))))
