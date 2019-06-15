(ns ports.config
  (:require [mount.core :refer [defstate]]
            [clojure.edn :as edn]))

(defn- new-config [path]
  (-> path slurp edn/read-string))

(defstate config :start (new-config "resources/config.edn"))
