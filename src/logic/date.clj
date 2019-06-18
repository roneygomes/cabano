(ns logic.date
  (:require [clojure.spec.alpha :as s]
            [spec.date])
  (:import [java.time LocalDate]
           [java.time.format DateTimeFormatter]))

(defn date-str->millis [date-str]
  (let [milliseconds-in-a-day (* 24 60 60 1000)]
    (-> (LocalDate/parse date-str DateTimeFormatter/ISO_DATE)
        (.toEpochDay)
        (* milliseconds-in-a-day))))

(s/fdef date-str->millis
  :args (s/cat :date-str :spec.date/iso-date-str)
  :ret number?)
