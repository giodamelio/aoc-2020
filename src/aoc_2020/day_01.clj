(ns aoc-2020.day-01)

(def input-example [1721 979 366 299 675 1456])
(def input-real [1810 1729 1857 1777 1927 1936 1797 1719 1703 1758 1768 2008 1963 1925 1919 1911 1782 2001 1744 1738 1742 1799 1765 1819 1888 127 1880 1984 1697 1760 1680 1951 1745 1817 1704 1736 1969 1705 1690 1848 1885 1912 1982 1895 1959 1769 1722 1807 1901 1983 1993 1871 1795 1955 1921 1934 1743 1899 1942 1964 1034 1952 1851 1716 1800 1771 1945 1877 1917 1930 1970 1948 1914 1767 1910 563 1121 1897 1946 1882 1739 1900 1714 1931 2000 311 1881 1876 354 1965 1842 1979 1998 1960 1852 1847 1938 1369 1780 1698 1753 1746 1868 1752 1802 1892 1755 1818 1913 1706 1862 326 1941 1926 1809 1879 1815 1939 1859 1999 1947 1898 1794 1737 1971 1977 1944 1812 1905 1359 1788 1754 1774 1825 1748 1701 1791 1786 1692 1894 1961 1902 1849 1967 1770 1987 1831 1728 1896 1805 1733 1918 1731 661 1776 1494 2005 2009 2004 1915 1695 1710 1804 1929 1725 1772 1933 609 1708 1822 1978 1811 1816 1073 1874 1845 1989 1696 1953 1823 1923 1907 1834 1806 1861 1785 297 1968 1764 1932 1937 1826 1732 1962 1916 1756 1975 1775 1922 1773])

(defn find-two-numbers-that-sum-to [target input]
  "Find the two numbers in a `input` list that sum to `target`"
  (first (for [a input
               b input
               :let [ab (+ a b)]
               :when (= ab target)]
           [a b])))

(defn find-three-numbers-that-sum-to [target input]
  "Find the three numbers in a `input` list that sum to `target`"
  (first (for [a input
               b input
               c input
               :let [abc (+ a b c)]
               :when (= abc target)]
           [a b c])))

(assert
  (= (find-two-numbers-that-sum-to 2020 input-example) [1721 299])
  "Find the example output")

(defn part-1 [target input]
  "Solve day 1 part 1"
  (apply * (find-two-numbers-that-sum-to target input)))

(defn part-2 [target input]
  "Solve day 1 part 2"
  (apply * (find-three-numbers-that-sum-to target input)))
