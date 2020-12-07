(ns aoc-2020.util-test
  (:require [clojure.test :refer :all]
            [aoc-2020.util :refer :all]))

(deftest get-capture-groups-test
  (testing "Lists the correct capture groups"
    (is (= (get-capture-groups #"(?<foo>\d)(?<bar>\d)") '("foo" "bar")))
    (is (= (get-capture-groups #"\d") '()))))

(deftest re-find-named-groups-test
  (testing "Handle Regex with no named groups"
    (is (= (re-find-named-groups #"\d [a-z] \d" "1 a 3")
           {})))

  (testing "Parse a simple grouped regex"
    (is (= (re-find-named-groups #"(?<first>\d) (?<second>[a-z]) (?<third>\d)" "1 a 3")
           {:first  "1"
            :second "a"
            :third  "3"})))

  (testing "Convert the numbers to ints"
    (is (= (re-find-named-groups #"(?<firstint>\d) (?<secondint>\d) (?<thirdint>\d)" "1 2 3")
           {:first  1
            :second 2
            :third  3}))))

