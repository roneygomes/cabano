(ns ports.http
  (:require [clj-http.client :as http]
            [clojure.data.json :as json]
            [clojure.string :as str]))

(defn- key-function [key-string]
  (-> key-string
      (str/lower-case)
      ;; removing numbers and dots from the keys
      (str/replace #"[0-9]*\. " "")
      ;; removing parentheses as well
      (str/replace #"[()]" "")
      (str/replace #" " "-")
      (keyword)))

(def ^:private url
  "https://www.alphavantage.co/query")

(defn daily-adjusted-prices! [symbol api-key]
  (let [query-params {"symbol"     symbol
                      "apikey"     api-key
                      "function"   "TIME_SERIES_DAILY_ADJUSTED"
                      "outputsize" "compact"}]
    (-> (http/get url {:query-params query-params})
        (:body)
        (json/read-str :key-fn key-function))))
