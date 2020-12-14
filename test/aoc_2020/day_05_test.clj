(ns aoc-2020.day-05-test
  (:require [clojure.test :refer :all]
            [aoc-2020.day-05 :refer :all]
            [aoc-2020.util :refer :all]))

(def input-example-entry "FBFBBFFRLR")
(def input-example "FBFBBFFRLR\nFBFBBFFRLR")

(deftest parse-input-test
  (is (= (parse-input input-example) ["FBFBBFFRLR" "FBFBBFFRLR"])))

(deftest convert-to-row-col-test
  (is (= (convert-to-row-col input-example-entry) [44 5])))

(deftest part-1-test
  (testing "Example"
    (is (= (part-1 input-example) 357)))
  (testing "Solution"
    (is (= (part-1 (get-puzzle-input 5)) 832))))

(deftest part-2-test
  (testing "Example"
    (is (= (part-2 input-example) 358)))
  (testing "Solution"
    (is (= (part-2 (get-puzzle-input 5)) 517))))
