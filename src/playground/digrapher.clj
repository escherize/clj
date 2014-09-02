(ns playground.digrapher
  (:use [dorothy.core]))

(defn digrapher [edge-lists]
  (-> edge-lists
      (into [])
      (digraph)
      dot
      (save! "out.png" {:format :png})))

(defn grapher [edge-lists]
  (-> edge-lists
      (into [])
      (graph)
      dot
      (save! "out.png" {:format :png})))

(digrapher   
  ;;start-state            end-state               weight  
  [[:date                  :date                  {:label "Aug 11, 2014"}]
   [:start                 :token-acquired        {:label "100"}]
   [:token-acquired        :idle                  {:label "100"}]
   [:idle                  :user-info-provided    {:label "10"}]
   [:idle                  :collecting-info       {:label "10"}]
   [:idle                  :displaying-promo      {:label "10"}]
   [:idle                  :purchase-made         {:label "15"}]
   [:idle                  :buckets-identified    {:label "50"}]
   [:idle                  :session-end           {:label "5"}]
   [:user-info-provided    :idle                  {:label "90"}]
   [:user-info-provided    :session-end           {:label "10"}]
   [:collecting-info       :idle                  {:label "90"}]
   [:collecting-info       :session-end           {:label "10"}]
   [:displaying-promo      :idle                  {:label "90"}]
   [:displaying-promo      :session-end           {:label "10"}]
   [:purchase-made         :session-end           {:label "100"}]
   [:buckets-identified    :buckets-confirmed     {:label "95"}]
   [:buckets-identified    :buckets-not-confirmed {:label "5"}]
   [:buckets-confirmed     :idle                  {:label "100"}]
   [:buckets-not-confirmed :idle                  {:label "100"}]
   [:session-end           :start                 {:label "10"}]
   [:session-end           :halt                  {:label "90"}]])

(comment
  (digrapher
   [[:start                 :token-acquired     {:label 100}]
    [:token-acquired        :idle               {:label 100}]
    [:idle                  :user-info-provided {:label 10}]
    [:idle                  :collecting-info    {:label 10}]
    [:idle                  :displaying-promo   {:label 10}]
    [:idle                  :purchase-made      {:label 15}]
    [:idle                  :buckets-identified {:label 50}]
    [:idle                  :session-end        {:label 05}]
    [:user-info-provided    :idle               {:label 90}]
    [:user-info-provided    :session-end        {:label 10}]
    [:collecting-info       :idle               {:label 90}]
    [:collecting-info       :session-end        {:label 10}]
    [:displaying-promo      :idle               {:label 90}]
    [:displaying-promo      :session-end        {:label 10}]
    [:purchase-made         :session-end        {:label 100}]
    [:buckets-identified    :buckets-confirmed  {:label 100}]
    [:buckets-confirmed     :idle               {:label 100}]
    [:buckets-not-confirmed :idle               {:label 100}]
    [:session-end           :start              {:label 95}]
    [:session-end           :halt               {:label 5}]])
  
)

