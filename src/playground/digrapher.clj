(ns playground.digrapher
  (:use [dorothy.core]))

(defn digrapher [edge-lists]
  (-> edge-lists
      (into [])
      (digraph)
      dot
      (save! "out.png" {:format :png})))

(def letters [:a :b :c :d :e])

(comment  ; program flow
  (digrapher [[:bucket :purchase-stats]
              [:purchases-by-bucket :purchase-stats]
              [:sessions-by-bucket :managed-sessions-by-bucket]
              [:sessions-by-bucket :unmanaged-sessions-by-bucket]
              [:managed-sessions-by-bucket :managed-session-stats]
              [:bucket :managed-session-stats]
              [:unmanaged-sessions-by-bucket :unmanaged-session-stats]
              [:bucket :unmanaged-session-stats]
              [:purchase-stats :sales-$]
              [:purchase-stats :discounts-$]
              [:purchase-stats :num-purchases]
              [:managed-session-stats :num-sessions]
              [:unmanaged-session-stats :num-unmanaged]
              [:sales-$ :aov]
              [:num-purchases :aov]
              [:num-purchases :conversion]
              [:num-sessions :conversion]
              [:discounts-$ :discounts-%]
              [:sales-$ :discount-$]
              [:bucket :type]
              [:bucket :name]])


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
              [:device-present? :create-session {:label :yes}]]))
