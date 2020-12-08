(ns aoc-2020.day-01-test
  (:require [clojure.test :refer :all]
            [aoc-2020.day-01 :refer :all]
            [aoc-2020.util :refer :all]))

(deftest find-two-numbers-that-sum-to-test
  (is (= (find-two-numbers-that-sum-to 6 [1 2 3 4 5]) [1 5]))
  (is (= (find-two-numbers-that-sum-to 3141 [1 2 3 4 5]) nil)))

(deftest find-three-numbers-that-sum-to-test
  (is (= (find-three-numbers-that-sum-to 12 [1 2 3 4 5]) [3 4 5]))
  (is (= (find-three-numbers-that-sum-to 3141 [1 2 3 4 5]) nil)))

(def input-example "1721\n979\n366\n299\n675\n1456")

(deftest part-1-test
  (testing "Example"
    (is (= (part-1 2020 input-example) 514579)))
  (testing "Solution"
    (is (= (part-1 2020 (get-puzzle-input 1)) 898299))))

(deftest part-2-test
  (testing "Example"
    (is (= (part-2 2020 input-example) 241861950)))
  (testing "Solution"
    (is (= (part-2 2020 (get-puzzle-input 1)) 143933922))))
