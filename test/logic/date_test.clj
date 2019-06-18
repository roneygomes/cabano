(ns logic.date-test
  (:require [logic.date :refer :all]
            [spec.date]
            [midje.sweet :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.properties :as prop]
            [midje.experimental :refer [for-all]]))

(tc/quick-check 100
   (prop/for-all [date spec.date/gen-local-date-str]
     (fact "will always generate a number"
       (date-str->millis date) => number?)))
