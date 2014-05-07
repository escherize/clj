(ns playground.digrapher
  (:use [dorothy.core]))

(defn digrapher [edge-lists]
  (->
   (digraph edge-lists)
   dot
   (save! "out.png" {:format :png})))

(digrapher [[:a :b :c]
            [:f :b]])

(comment 
  (digrapher [[:session-added-to-partition? {:shape :diamond}]
              [:session-present? {:shape :diamond}]
              [:device-present? {:shape :diamond}]
              [:partition-present? {:shape :diamond}]

              [:device-tracker-id {:shape :none}]
              [:user-token {:shape :none}]
              [:current-run-ids {:shape :none}]

              [:return {:shape :square}]
              ;; edges
              [:device-tracker-id :session-present?]
              [:user-token :session-present?]
              [:session-present?
               :partition-present?
               :session-added-to-partition?
               :return {:label :yes}]
              [:session-present?
               :device-present?
               :create-device {:label :no}]
              [:current-run-ids :partition-present?]
              [:partition-present?
               :create-partition
               :add-partition-to-runs
               :add-device-to-partition
               :add-session-to-partition
               :return
               {:label :no}]
              [:session-added-to-partition?
               :add-session-to-partition
               {:label :no}]
              [:create-device :create-session :partition-present?]
              [:device-present? :create-session {:label :yes}]])
  )
