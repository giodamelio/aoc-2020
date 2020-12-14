(ns aoc-2020.day-05
  (:require [aoc-2020.util :refer :all]
            [clojure.string :as str]))

(defn parse-input [input]
  (str/split-lines input))

(defn convert-to-row-col [seat]
  "Convert a seat specification to a row and column"
  (let [row-section (subs seat 0 7)
        col-section (subs seat 7)
        row-binary (str/replace (str/replace row-section #"B" "1") #"F" "0")
        col-binary (str/replace (str/replace col-section #"R" "1") #"L" "0")
        row-int (Integer/parseInt row-binary 2)
        col-int (Integer/parseInt col-binary 2)]
    [row-int col-int]))

(defn row-col-to-id [[row col]]
  (+ (* row 8) col))

(defn part-1 [input]
  "Solve day 5 part 1"
  (->> input
       (parse-input)
       (map convert-to-row-col)
       (map row-col-to-id)
       (apply max)))

(partition 2 1 [1 2 3 4 6 7 8 9])

(defn part-2 [input]
  "Solve day 5 part 2"
  (->> input
       (parse-input)
       (map convert-to-row-col)
       (map row-col-to-id)
       (sort)
       ;; Split into pairs of adjacent numbers
       (partition 2 1)
       ;; Look for a pair with a difference of more then 1
       (filter #(not= 1 (- (second %) (first %))))
       ;; Get the number that should be in the middle
       (first)
       (first)
       (+ 1)))
