(ns main
  (:require [ports.http]
            [adapters.price]))

(defn -main [& {:as args}]
  (->> (ports.http/daily-adjusted-prices! "ABEV3.SA" "HGHRH3AUEGWG6V8Z")
       (:time-series-daily)
       (map (fn [[k v]] (adapters.price/wire->internal {k v})))
       (clojure.pprint/pprint)))
