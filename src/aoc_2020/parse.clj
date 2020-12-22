(ns aoc-2020.parse
  (:require [clojure.string :as str]))

(defn take-pattern
  "Take a pattern and an input, return a hash and the remaining input"
  ([[pattern-name pattern converter] input]
   (let [match (re-find (re-matcher pattern input))
         clipped-input (subs input (count match))]
     (if match
       (if converter
        [{pattern-name (apply converter [match])} clipped-input]
        [{pattern-name match} clipped-input])
       [{} clipped-input]))))

(defn parse [patterns input]
  "Parse a string based on a series of regexes"
  (loop [[pattern & remaining-patterns] patterns
         output {}
         remaining-input input]
    (if (empty? remaining-input)
      output
      (cond
        ;; Ignore all whitespace
        (= pattern [:ignore-whitespace])
        (recur remaining-patterns output (str/triml remaining-input))
        ;; Ignore something specific
        (= (first pattern) :ignore)
        (let [[_ remaining-input] (take-pattern pattern remaining-input)]
          (recur remaining-patterns output remaining-input))
        ;; Parse the pattern
        :else
        (let [[new-output remaining-input] (take-pattern pattern remaining-input)]
          (recur remaining-patterns (merge output new-output) remaining-input))))))

(def number [:int #"\d+"])
(def letters #"[a-z]+")
(def line [number " " letters "\n"])
(def lines [:one-or-more line])
