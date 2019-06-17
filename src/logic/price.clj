(ns logic.price
  (:require [logic.date]))

(defn- str->double [str]
  (try
    (Double/parseDouble str)
    (catch Exception e
      0)))

(defn new-point
  [symbol
   {:keys [split-coefficient, open, close, volume, high, low, adjusted-close, date]}]
  {:measurement "daily_adjusted_price"
   :time        (logic.date/date-str->millis date)
   :fields      {:split-coefficient (str->double split-coefficient)
                 :open              (str->double open)
                 :close             (str->double close)
                 :high              (str->double high)
                 :low               (str->double low)
                 :adjusted-close    (str->double adjusted-close)}
   :tags        {:symbol symbol}})

