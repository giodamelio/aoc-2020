(ns aoc-2020.day-02
  (:require [aoc-2020.util :refer :all]
            [clojure.string :as str]))

(defn parse-entry [line]
  (re-find-named-groups #"(?<lowint>\d+)-(?<highint>\d+) (?<requiredchar>[a-z]): (?<password>.*)$" line))

(defn parse-input [input]
  (->> input
       (str/split-lines)
       (map parse-entry)))

(defn xor [a b]
  "Logically XOR two values"
  (if a
    (if b nil a)
    (if b b nil)))

(defn is-valid-sled [{:keys [low high requiredchar password]}]
  (as-> (str/split password #"") $
        (frequencies $)
        (get $ requiredchar 0)
        (<= low $ high)))

(defn is-valid-toboggan [{:keys [low high requiredchar password]}]
  (as-> (str/split password #"") $
        [(get $ (dec low)) (get $ (dec high))]
       (map (partial = requiredchar) $)
       (apply xor $)))

(defn part-1 [input]
  "Solve day 1 part 1"
  (->> input
       (parse-input)
       (filter is-valid-sled)
       (count)))

(defn part-2 [input]
  "Solve day 1 part 2"
  (->> input
       (parse-input)
       (filter is-valid-toboggan)
       (count)))
