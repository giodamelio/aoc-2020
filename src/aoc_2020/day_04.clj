(ns aoc-2020.day-04
  (:require [aoc-2020.util :refer :all]
            [clojure.string :as str]))

(defn parse-entry [entry]
  (->> (str/split entry #" ")
       ;; Split by colon
       (map #(str/split % #":"))
       ;; Convert keys into keywords
       (map (fn [[key value]] [(keyword key) value]))
       ;; Convert into a map
       (into {})))

(defn parse-input [input]
  (as-> input $
    ;; Split input by empty lines
    (str/split $ #"\n\n")
    ;; Replace newlines in each entry with spaces
    (map #(str/replace % #"\n" " ") $)
    ;; Parse each individual entry
    (map parse-entry $)))

(defn valid-keys? [passport]
  (-> passport
      ;; Get a list of all the keys
      (keys)
      ;; Throw it into an set
      (set)
      ;; Check that every item in a list is in the set
      (every? [:ecl :byr :iyr :hgt :pid :hcl :eyr])))

(defn valid-height? [height]
  (cond
    (str/ends-with? height "in") (<= 59 (parse-int height) 76)
    (str/ends-with? height "cm") (<= 150 (parse-int height) 193)
    :else false))

(defn valid-passport? [passport]
  (and (valid-keys? passport)
       ;; Birth Year between 1920 and 2002
       (<= 1920 (parse-int (:byr passport)) 2002)
       ;; Issue Year between 2010 and 2020
       (<= 2010 (parse-int (:iyr passport)) 2020)
       ;; Expiration Year between 2020 and 2030
       (<= 2020 (parse-int (:eyr passport)) 2030)
       ;; Height within valid range
       (valid-height? (:hgt passport))
       ;; Hair Color is valid RGB hex color
       (boolean (re-matches #"#[0-9a-f]{6}" (:hcl passport)))
       ;; Eye Color is on the defined list
       (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} (:ecl passport))
       ;; Passport ID is a nine digit number (including leading zeros)
       (boolean (re-matches #"[0-9]{9}" (:pid passport)))))

(defn part-1 [input]
  "Solve day 4 part 1"
  (->> input
      (parse-input)
      (filter valid-keys?)
      (count)))

(defn part-2 [input]
  "Solve day 4 part 2"
  (->> input
      (parse-input)
      (filter valid-passport?)
      (count)))
