(ns spec.price
  (:require [clojure.spec.alpha :as s]
            [spec.date]))

(s/def ::high double?)
(s/def ::low double?)
(s/def ::open double?)
(s/def ::close double?)
(s/def ::adjusted-close double?)
(s/def ::split-coefficient double?)

(s/def ::price (s/keys :req-un [::high
                                ::low
                                ::open
                                ::close
                                ::adjusted-close
                                ::split-coefficient]))

(s/def ::price-response (s/map-of :spec.date/iso-date-str ::price))

(comment
  ;; This is how a price response looks like.
  (s/explain ::price-response {:2019-01-31 {:split-coefficient 1.0,
                                            :open              6.85,
                                            :close             6.56,
                                            :high              6.85,
                                            :low               6.5,
                                            :adjusted-close    6.4085}}))


