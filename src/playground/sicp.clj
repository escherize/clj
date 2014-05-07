(ns playground.sicp)
(defn ex-two-two []
  
  (defn make-segment [p1 p2]
    {:point1 p1 :point2 p2})

  (defn make-point [x y]
    [x y])

  (defn midpoint-segment [line]
    (let [x1 (first (:point1 line))
          x2 (first (:point2 line))
          y1 (second (:point1 line))
          y2 (second (:point2 line))]
      {:point1 (/ (+ x1 x2) 2)
       :point2 (/ (+ y1 y2) 2)}))

  (midpoint-segment (make-segment
                     (make-point 0 0)
                     (make-point 1 1))))

(defn ex-two-three []
  "Implement a representation for rectangles in a plane."

  (defn abs [x]
    (if (<= 0 x) x (- x)))
  
  (defn rect
    ([width height]
       (rect 0 0 width height))
    ([x1 y1 x2 y2]
       {:width (abs (- x1 x2))
        :height (abs (- y1 y2))}))

  (defn perimeter [rect]
    (* 2 (+ (:width rect) (:height rect))))

  (defn area [rect]
    (* (:width rect) (:height rect)))

  (let [rect1 (rect 10 5)
        rect2 (rect 1 1 11 6)]
    (assert (= (area rect1)
               (area rect2)))
    (assert (= (perimeter rect1)
               (perimeter rect2)))))

(ex-two-three)

