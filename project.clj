(defproject playground "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure            "1.6.0"]
                 [compojure                      "1.1.9"]
                 [dorothy                        "0.0.5"]
                 [enlive                         "1.1.5"]
                 [org.clojure/data.generators    "0.1.2"]
                 [clj-http                       "1.0.0"]
                 [hiccup                         "1.0.5"]
                 [print-foo                      "0.5.3"]
                 [org.clojure/math.combinatorics "0.0.7"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]]
  :plugins [[cider/cider-nrepl "0.7.0"]])
