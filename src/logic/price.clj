(ns logic.price
  (:import (java.time LocalDate)
           (java.time.format DateTimeFormatter)))

(defn- date-str->millis [date-str]
  (let [milliseconds-in-a-day (* 24 60 60 1000)]
    (-> (LocalDate/parse date-str DateTimeFormatter/ISO_DATE)
        (.toEpochDay)
        (* milliseconds-in-a-day))))

(defn- str->double [str]
  (try
    (Double/parseDouble str)
    (catch Exception e
      0)))

(defn new-point
  [symbol
   {:keys [split-coefficient, open, close, volume, high, low, adjusted-close, date]}]
  {:measurement "daily_adjusted_price"
   :time        (date-str->millis date)
   :fields      {:split-coefficient (str->double split-coefficient)
                 :open              (str->double open)
                 :close             (str->double close)
                 :high              (str->double high)
                 :low               (str->double low)
                 :adjusted-close    (str->double adjusted-close)}
   :tags        {:symbol symbol}})

