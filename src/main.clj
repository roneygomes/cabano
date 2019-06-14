(ns main
  (:require [ports.http]
            [adapters.price]
            [logic.stock])
  (:import (java.lang Thread)))

(defn fetch-price! [symbol]
  (->> (ports.http/daily-adjusted-prices! symbol "HGHRH3AUEGWG6V8Z")
       (:time-series-daily)
       (map (fn [[k v]] (adapters.price/wire->internal {k v})))
       (map #(logic.price/new-point symbol %))))

(defn print-and-pause! [point]
  (clojure.pprint/pprint point)
  (Thread/sleep 13000))

(defn -main [& {:as args}]
  (mapv #(-> % fetch-price! print-and-pause!) logic.stock/symbols))

