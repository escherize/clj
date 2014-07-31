(ns playground.digrapher
  (:use [dorothy.core]))

(defn digrapher [edge-lists]
  (-> edge-lists
      (into [])
      (digraph)
      dot
      (save! "out.png" {:format :png})))

(def letters [:a :b :c :d :e])


(digrapher [[:farm :apple :pie :mouth]])

(digrapher [[:start                 :token-acquired              {:label 100} ]
            [:token-acquired        :idle                        {:label 100} ]
            [:idle                  :user-info-provided          {:label 10} ]
            [:idle                  :collecting-info             {:label 10} ]
            [:idle                  :displaying-promo            {:label 10} ]
            [:idle                  :purchase-made               {:label 15} ]
            [:idle                  :buckets-identified          {:label 50} ]
            [:idle                  :session-end                 {:label 05} ]
            [:user-info-provided    :idle                        {:label 90} ]
            [:user-info-provided    :session-end                 {:label 10} ]
            [:collecting-info       :idle                        {:label 90} ]
            [:collecting-info       :session-end                 {:label 10} ]
            [:displaying-promo      :idle                        {:label 90} ]
            [:displaying-promo      :session-end                 {:label 10} ]
            [:purchase-made         :session-end                 {:label 100} ]
            [:buckets-identified    :buckets-confirmed           {:label 95} ]
            [:buckets-identified    :buckets-not-confirmed       {:label  5} ]
            [:buckets-confirmed     :idle                        {:label 100} ]
            [:buckets-not-confirmed :idle                        {:label 100} ]
            [:session-end           :start                       {:label 50} ]
            [:session-end           :halt                        {:label 50} ]])

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
