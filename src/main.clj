(ns main
  (:require [ports.http]
            [ports.db]
            [adapters.price]
            [logic.price]
            [logic.stock]
            [mount.core])
  (:import (java.lang Thread)))

(defn fetch-price! [symbol]
  (->> (ports.http/daily-adjusted-prices! symbol "HGHRH3AUEGWG6V8Z")
       (:time-series-daily)
       (map (fn [[k v]] (adapters.price/wire->internal {k v})))
       (map #(logic.price/new-point symbol %))))

(defn upsert-and-pause! [points]
  (mapv ports.db/upsert-price-point! points)
  (Thread/sleep 13000))

(defn -main [& {:as args}]
  (mount.core/start)
  (mapv #(-> % fetch-price! upsert-and-pause!) logic.stock/symbols))

