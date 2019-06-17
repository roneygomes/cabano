(ns adapters.price)

(defn wire->internal [price]
  (let [date-key (-> price keys first)]
    (-> (date-key price)
        (assoc :date (name date-key)))))
