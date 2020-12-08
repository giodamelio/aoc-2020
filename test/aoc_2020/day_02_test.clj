(ns aoc-2020.day-02-test
  (:require [clojure.test :refer :all]
            [aoc-2020.day-02 :refer :all]
            [aoc-2020.util :refer :all]))

(deftest parse-entry-test
  (testing "Parse an example entry"
    (is (= (parse-entry "1-100 a: aaaaaaa")
           {:low 1
            :high 100
            :requiredchar "a"
            :password "aaaaaaa"}))))

(deftest parse-input-test
  (testing "Parse a list of entries"
    (is (= (parse-input "1-100 a: aaaaaaa\n2-3 b: bbb")
           [{:low 1
             :high 100
             :requiredchar "a"
             :password "aaaaaaa"}
            {:high         3
             :low          2
             :requiredchar "b"
             :password     "bbb"}]))))

(deftest xor-test
  (is (= (xor true true) nil))
  (is (= (xor false true) true))
  (is (= (xor true false) true))
  (is (= (xor false false) nil)))

(deftest is-valid-sled-test
  (testing "Valid"
    (is (is-valid-sled {:low 1 :high 2 :requiredchar "b" :password "abcabc"}))
    (is (is-valid-sled {:low 1 :high 3 :requiredchar "b" :password "abcabcabc"})))
  (testing "Invalid"
    (is (not (is-valid-sled {:low 1 :high 2 :requiredchar "b" :password "abcabcabc"})))
    (is (not (is-valid-sled {:low 1 :high 2 :requiredchar "b" :password "acacacaca"})))))

(deftest is-valid-toboggan-test
  (testing "Valid"
    (is (is-valid-toboggan {:low 1 :high 2 :requiredchar "b" :password "abcabc"}))
    (is (is-valid-toboggan {:low 1 :high 3 :requiredchar "c" :password "abcabcabc"})))
  (testing "Invalid"
    (is (not (is-valid-toboggan {:low 1 :high 2 :requiredchar "b" :password "bbcabcabc"})))
    (is (not (is-valid-toboggan {:low 1 :high 2 :requiredchar "b" :password "acacacaca"})))))

(def input-example "1-3 a: abcde\n1-3 b: cdefg\n2-9 c: ccccccccc")

(deftest part-1-test
  (testing "Example"
    (is (= (part-1 input-example) 2)))
  (testing "Solution"
    (is (= (part-1 (get-puzzle-input 2)) 528))))

(deftest part-2-test
  (testing "Example"
    (is (= (part-2 input-example) 1)))
  (testing "Solution"
    (is (= (part-2 (get-puzzle-input 2)) 497))))
