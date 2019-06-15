(ns ports.db
  (:require [mount.core :refer [defstate]]
            [influxdb-clojure.core :as influxdb]
            [ports.config :refer [config]]
            [clojure.pprint]))

(defn- new-client [{{:keys [uri user password]} :db}]
  (influxdb/connect uri user password))

(defstate client :start (new-client config))

(defn upsert-price-point! [point]
  (let [db-name (-> config :db :name)
        opts    {:retention-policy "autogen"}]
    (clojure.pprint/pprint point)
    (influxdb/write-points client db-name [point] opts)))

