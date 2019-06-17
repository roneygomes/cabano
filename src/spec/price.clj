(ns spec.price
  (:require [clojure.spec.alpha :as s]))

(defn iso-date? [str]
  (re-matches #"\d{4}-([0]\d|1[0-2])-([0-2]\d|3[01])" str))

(s/def ::high double?)
(s/def ::low double?)
(s/def ::open double?)
(s/def ::close double?)
(s/def ::adjusted-close double?)
(s/def ::split-coefficient double?)
(s/def ::iso-date (s/and keyword? #(-> % name iso-date?)))

(s/def ::price (s/keys :req-un [::high
                                ::low
                                ::open
                                ::close
                                ::adjusted-close
                                ::split-coefficient]))

(s/def ::price-response (s/map-of ::iso-date ::price))

(comment
  (s/explain ::price-response {:2019-01-31 {:split-coefficient 1.0,
                                            :open              6.85,
                                            :close             6.56,
                                            :high              6.85,
                                            :low               6.5,
                                            :adjusted-close    6.4085}}))


