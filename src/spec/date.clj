(ns spec.date
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test.check.generators])
  (:import [java.time ZoneOffset LocalDateTime Instant]))

(def gen-local-date-str
  (gen/fmap #(-> (Instant/ofEpochMilli %)
                 (LocalDateTime/ofInstant ZoneOffset/UTC)
                 (.toLocalDate)
                 (str))
            (gen/large-integer)))

(s/def ::iso-date-str
  (s/with-gen
    (s/and string? #(re-matches #"^\d{4}-\d{2}-\d{2}$" %))
    (constantly gen-local-date-str)))
