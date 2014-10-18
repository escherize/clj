(ns playground.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))





(defn start!
  ([]
     (start! {:port default-port :nrepl-port default-nrepl-port}))
  ([{:keys [port nrepl-port]}]
     (init/init! "eccentrica-reporting")

     (log/info {:message "Starting nRepl server"})
     (nrepl/start-server :port nrepl-port)

     (log/info {:message "Starting Eccentrica Reporting HTTP server..."})
     (reset! server (jetty/run-jetty #'handler {:port port :join? false}))))
