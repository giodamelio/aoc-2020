(ns aoc-2020.day-01
  (:require [aoc-2020.util :refer :all]
            [clojure.string :as str]))

(defn parse-input [input]
  (->> input
       (str/split-lines)
       (map parse-int)))

(defn find-two-numbers-that-sum-to [target input]
  "Find the two numbers in a `input` list that sum to `target`"
  (first (for [a input
               b input
               :let [ab (+ a b)]
               :when (= ab target)]
           [a b])))

(defn find-three-numbers-that-sum-to [target input]
  "Find the three numbers in a `input` list that sum to `target`"
  (first
    (for [a input
          b input
          c input
          :let [abc (+ a b c)]
          :when (and (= abc target) (not= a b) (not= b c) (not= c a))]
      [a b c])))

(defn part-1 [target input]
  "Solve day 1 part 1"
  (->> input
       (parse-input)
       (find-two-numbers-that-sum-to target)
       (apply *)))

(defn part-2 [target input]
  "Solve day 1 part 2"
  (->> input
       (parse-input)
       (find-three-numbers-that-sum-to target)
       (apply *)))
