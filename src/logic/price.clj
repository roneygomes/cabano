(ns logic.price
  (:require [java-time])
  (:import (java.time LocalDate)
           (java.time.format DateTimeFormatter)))

(defn date-str->millis [date-str]
  (let [milliseconds-in-a-day (* 24 60 60 1000)]
    (-> (LocalDate/parse date-str DateTimeFormatter/ISO_DATE)
        (.toEpochDay)
        (* milliseconds-in-a-day))))

(defn new-point
  [symbol
   {:keys [split-coefficient, open, close, volume, high, low, adjusted-close, date]}]
  {:measurement "daily_adjusted_price"
   :time        (date-str->millis date)
   :fields      {:split-coefficient split-coefficient
                 :open              open
                 :close             close
                 :high              high
                 :low               low
                 :adjusted-close    adjusted-close}
   :tags        {:symbol symbol}})


