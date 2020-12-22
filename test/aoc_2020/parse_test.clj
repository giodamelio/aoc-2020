(ns aoc-2020.parse-test
  (:require [clojure.test :refer :all]
            [aoc-2020.parse :refer :all]
            [aoc-2020.util :refer :all]))

(deftest take-pattern-test
  (testing "Simple pattern"
    (is (= (take-pattern [:number #"\d+"] "2002 abc")
           [{:number "2002"} " abc"]))
    (is (= (take-pattern [:number #".*"] "2002 abc")
           [{:number "2002 abc"} ""])))
  (testing "No input"
    (is (= (take-pattern [:number #"\d+"] "")
           [{} ""])))
  (testing "Use converter"
    (is (= (take-pattern [:number #"\d+" parse-int] "2002")
           [{:number 2002} ""]))))

(deftest parse-test
  (testing "Simple set of patterns"
    (is (= (parse
             [[:numbers #"\d+"]
              [:space   #" "]
              [:letters #"[a-z]+"]]
             "2002 abc")
           {:numbers "2002"
            :space " "
            :letters "abc"})))
  (testing "Ignore whitespace"
    (is (= (parse
             [[:numbers #"\d+"]
              :ignore-whitespace
              [:letters #"[a-z]+"]]
             "2002 abc")
           {:numbers "2002"
            :letters "abc"})))
  (testing "With converters"
    (is (= (parse
             [[:numbers #"\d+" parse-int]
              :ignore-whitespace
              [:letters #"[a-z]+" count]]
             "2002 abc")
           {:numbers 2002
            :letters 3}))))
