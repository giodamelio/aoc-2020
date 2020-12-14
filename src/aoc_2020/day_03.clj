(ns aoc-2020.day-03
  (:require [aoc-2020.util :refer :all]
            [clojure.string :as str]))

(defn char-to-keyword [char]
  (case char
    "." :snow
    "#" :tree))

(defn keyword-to-emoji [char]
  (case char
    :snow "⬜"
    :tree "\uD83C\uDF84"
    :player "\uD83E\uDDD1"))

(defn parse-input [input]
  (->> input
        (str/split-lines)
        (map #(str/split % #""))
        (map #(map char-to-keyword %))))

(defn render-board [board]
  "Renders a board with Emoji"
  (->> board
       (map #(map keyword-to-emoji %))
       (map #(str/join %))
       (str/join "\n")
       (println)))

(defn part-1
  "Solve day 3 part 1"
  ([input] (part-1 input [0 0] [3 1]))
  ([input starting-location slope]
   (let [board (parse-input input)]
     ; Convert each row to an infinitely repeating sequence
     (map cycle board))))

(defn part-2 [input]
  "Solve day 3 part 2"
  (->> input
       (parse-input)))
