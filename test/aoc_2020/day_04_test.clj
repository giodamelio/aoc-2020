(ns aoc-2020.day-04-test
  (:require [clojure.test :refer :all]
            [aoc-2020.day-04 :refer :all]
            [aoc-2020.util :refer :all]))

(def input-example "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in")

(deftest parse-entry-test
  (is (= (parse-entry "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm")
         {:ecl "gry",
          :pid "860033327",
          :eyr "2020",
          :hcl "#fffffd",
          :byr "1937",
          :iyr "2017",
          :cid "147",
          :hgt "183cm"})))

(deftest parse-input-test
  (is (= (parse-input input-example)
         '({:ecl "gry",
            :pid "860033327",
            :eyr "2020",
            :hcl "#fffffd",
            :byr "1937",
            :iyr "2017",
            :cid "147",
            :hgt "183cm"}
           {:iyr "2013",
            :ecl "amb",
            :cid "350",
            :eyr "2023",
            :pid "028048884",
            :hcl "#cfa07d",
            :byr "1929"}
           {:hcl "#ae17e1",
            :iyr "2013",
            :eyr "2024",
            :ecl "brn",
            :pid "760753108",
            :byr "1931",
            :hgt "179cm"}
           {:hcl "#cfa07d",
            :eyr "2025",
            :pid "166559648",
            :iyr "2011",
            :ecl "brn",
            :hgt "59in"}))))

(deftest valid-keys?-test
  (testing "Valid"
    ;; Normal passport
    (is (= (valid-keys? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}) true))
    ;; North Pole Credential, only missing cid
    (is (= (valid-keys? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :hgt "183cm"}) true)))
  (testing "Valid"
    ;; Missing height
    (is (= (valid-keys? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147"}) false))))

(deftest valid-height?-test
  (testing "Inches"
    (is (= (valid-height? "58in") false))
    (is (= (valid-height? "59in") true))
    (is (= (valid-height? "76in") true))
    (is (= (valid-height? "77in") false)))
  (testing "Centimeters"
    (is (= (valid-height? "149cm") false))
    (is (= (valid-height? "150cm") true))
    (is (= (valid-height? "193cm") true))
    (is (= (valid-height? "194cm") false))))

(deftest valid-passport?-test
  (testing "Birth Year between 1920 and 2002"
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1919", :iyr "2017", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2017", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "2002", :iyr "2017", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "2003", :iyr "2017", :cid "147", :hgt "183cm"}) false)))
  (testing "Issue Year between 2010 and 2020"
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2009", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "2002", :iyr "2020", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "2002", :iyr "2021", :cid "147", :hgt "183cm"}) false)))
  (testing "Expiration Year between 2020 and 2030"
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2019", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2030", :hcl "#fffffd", :byr "2002", :iyr "2020", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2031", :hcl "#fffffd", :byr "2002", :iyr "2020", :cid "147", :hgt "183cm"}) false)))
  (testing "Height in valid range"
    (testing "Inches"
      (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2030", :hcl "#fffffd", :byr "2002", :iyr "2020", :cid "147", :hgt "62in"}) true))
      (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2030", :hcl "#fffffd", :byr "2002", :iyr "2020", :cid "147", :hgt "2in"}) false)))
    (testing "Centimeters"
      (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "2cm"}) false))
      (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "155cm"}) true))))
  (testing "Hair Color is valid RGB hex color"
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffg", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fff0fd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) true))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2030", :hcl "fffffd", :byr "2002", :iyr "2020", :cid "147", :hgt "183cm"}) false)))
  (testing "Eye Color is on the defined list"
    (is (= (valid-passport? {:ecl "gre", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) true)))
  (testing "Passport ID is a nine digit number (including leading zeros)"
    (is (= (valid-passport? {:ecl "gry", :pid "86003332", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "8600333270", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) false))
    (is (= (valid-passport? {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1920", :iyr "2010", :cid "147", :hgt "183cm"}) true))))

(deftest part-1-test
  (testing "Example"
    (is (= (part-1 input-example) 2)))
  (testing "Solution"
    (is (= (part-1 (get-puzzle-input 4)) 206))))

(deftest part-2-test
  (testing "Example"
    (is (= (part-2 input-example) 2)))
  (testing "Solution"
    (is (= (part-2 (get-puzzle-input 4)) 123))))
