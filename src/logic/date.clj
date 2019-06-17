(ns logic.date
  (:import [java.time LocalDate]
           [java.time.format DateTimeFormatter]))

;; TODO: test this.
(defn iso-date? [str]
  (re-matches #"\d{4}-([0]\d|1[0-2])-([0-2]\d|3[01])" str))

;; TODO: test this.
(defn date-str->millis [date-str]
  (let [milliseconds-in-a-day (* 24 60 60 1000)]
    (-> (LocalDate/parse date-str DateTimeFormatter/ISO_DATE)
        (.toEpochDay)
        (* milliseconds-in-a-day))))

