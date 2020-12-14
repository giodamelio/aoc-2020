(ns aoc-2020.day-03-test
  (:require [clojure.test :refer :all]
            [aoc-2020.day-03 :refer :all]
            [aoc-2020.util :refer :all]))

(def input-example "..##.......\n#...#...#..\n.#....#..#.\n..#.#...#.#\n.#...##..#.\n..#.##.....\n.#.#.#....#\n.#........#\n#.##...#...\n#...##....#\n.#..#...#.#")
(def input-example-board '((:snow :snow :tree :tree :snow :snow :snow :snow :snow :snow :snow)
                           (:tree :snow :snow :snow :tree :snow :snow :snow :tree :snow :snow)
                           (:snow :tree :snow :snow :snow :snow :tree :snow :snow :tree :snow)
                           (:snow :snow :tree :snow :tree :snow :snow :snow :tree :snow :tree)
                           (:snow :tree :snow :snow :snow :tree :tree :snow :snow :tree :snow)
                           (:snow :snow :tree :snow :tree :tree :snow :snow :snow :snow :snow)
                           (:snow :tree :snow :tree :snow :tree :snow :snow :snow :snow :tree)
                           (:snow :tree :snow :snow :snow :snow :snow :snow :snow :snow :tree)
                           (:tree :snow :tree :tree :snow :snow :snow :tree :snow :snow :snow)
                           (:tree :snow :snow :snow :tree :tree :snow :snow :snow :snow :tree)
                           (:snow :tree :snow :snow :tree :snow :snow :snow :tree :snow :tree)))

(deftest parse-input-test
  (is (= (parse-input input-example)
         input-example-board)))

(deftest part-1-test
  (testing "Example"
    (is (= (part-1 input-example) 0))))
  ;(testing "Solution"
  ;  (is (= (part-1 (get-puzzle-input 3)) 0))))

(deftest part-2-test
  (testing "Example"
    (is (= (part-2 input-example) 0))))
  ;(testing "Solution"
  ;  (is (= (part-2 (get-puzzle-input 3)) 0))))