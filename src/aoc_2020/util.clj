(ns aoc-2020.util
  (:require [clojure.string :as str]))

(defn parse-int
  "Parse a string to an int"
  [string]
  (Integer/parseInt (re-find #"\A[-+]?\d+" string)))

(defn get-capture-groups
  "List the capture groups in a regex"
  [re]
  (->> re
       (.pattern)
       (.toString)
       (re-seq #"\(\?<([a-zA-Z][a-zA-Z0-9]*)")
       (map second)))

(defn re-find-named-groups
  "Return a map of name capture groups from a regex"
  [re string]
  (let [matcher (re-matcher re string)
        groups (get-capture-groups matcher)]
    (if (.find matcher)
      (loop [[first & rest] groups
             output {}]
        (if (nil? first)
          output
          (recur rest
                 (if (str/ends-with? first "int")
                   (assoc output
                     (keyword (str/replace first #"int$" ""))
                     (parse-int (.group matcher first)))
                   (assoc output
                     (keyword first)
                     (.group matcher first))))))
      {})))