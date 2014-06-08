;; 1 Nothing but the Truth

(= true true)

;; 2 Simple Math

(= (- 10 (* 2 3)) 4)

;; 3 Intro to Strings

(= "HELLO WORLD" (.toUpperCase "hello world"))


;; 4 Intro to Lists

(= (list :a :b :c) '(:a :b :c))


;; 5 Lists: conj

(= '(1 2 3 4) (conj '(2 3 4) 1))

(= '(1 2 3 4) (conj '(3 4) 2 1))


;; 6 Intro to Vectors

(= '(:a, :b, :c) (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))


;; 7 Vectors: conj

(= [1 2 3 4] (conj [1 2 3] 4))

(= [1 2 3 4] (conj [1 2] 3 4))


;; 8 Intro to Sets

(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))

(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))


;; 9 Sets: conj

(= #{1 2 3 4} (conj #{1 4 3} 2))


;; 10 Intro to Maps

(= 20 ((hash-map :a 10, :b 20, :c 30) :b))

(= 20 (:b {:a 10, :b 20, :c 30}))


;; 11 Maps: conj

(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))


;; 12 Intro to colluences

(= 3 (first '(3 2 1)))

(= 3 (second [2 3 4]))

(= 3 (last (list 1 2 3)))


;; 13 colluences: rest

(= [20 30 40] (rest [10 20 30 40]))


;; 14 Intro to Functions

(= 8 ((fn add-five [x] (+ x 5)) 3))

(= 8 ((fn [x] (+ x 5)) 3))

(= 8 (#(+ % 5) 3))

(= 8 ((partial + 5) 3))


;; 15 Double Down

(= ((fn double-down [x] (* x 2)) 2) 4)

(= ((fn [x] (* x 2)) 3) 6)

(= (#(* % 2) 11) 22)

(= ((partial * 2) 7) 14)


;; 16 Hello World

(defn greeting [name] (format "Hello, %s!" name))

(= (greeting "Dave") "Hello, Dave!")

(= (greeting "Jenn") "Hello, Jenn!")

(= (greeting "Rhea") "Hello, Rhea!")


;; 17 colluences: map

(= '(6 7 8) (map #(+ % 5) '(1 2 3)))


;; 18 colluences: filter

(= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))


;; 19 Last Element

;; Only relying on first/rest
(defn coll-last [coll]
   (if (empty? (rest coll))
     (first coll)
     (coll-last (rest coll))))

;; Using more seq functions
(defn coll-last [coll]
  (first (reverse coll)))

(= (coll-last [1 2 3 4 5]) 5)

(= (coll-last '(5 4 3)) 3)

(= (coll-last ["b" "c" "d"]) "d")


;; 20 Penultimate Element

;; Only relying on first/rest
;; O(2N)
(defn penultimate [coll]
  (if (<= (count (rest coll)) 1)
    (first coll)
    (recur (rest coll))))

;; Using more seq functions
;; O(2N)
(defn penultimate [coll] (last (butlast coll)))

;; Using more seq functions
;; O(N)
(defn penultimate [coll] (fnext (reverse coll)))

(= (penultimate (list 1 2 3 4 5)) 4)

(= (penultimate ["a" "b" "c"]) "b")

(= (penultimate [[1 2] [3 4]]) [1 2])


;; 21 Nth Element

;; Only relying on first/rest
(defn coll-nth [coll n]
  (if (= n 0)
    (first coll)
    (recur (rest coll) (dec n))))

;; Using more seq functions
(defn coll-nth [coll n]
  (first (drop n coll)))

(= (coll-nth '(4 5 6 7) 2) 6)

(= (coll-nth [:a :b :c] 0) :a)

(= (coll-nth [1 2 3 4] 1) 2)

(= (coll-nth '([1 2] [3 4] [5 6]) 2) [5 6])


;; 22 Count a Sequence

;; Only relying on first/rest
(defn coll-count [coll]
  (loop [count 0
         coll-to-count coll]
    (if (empty? coll-to-count)
      count
      (recur (inc count) (rest coll-to-count)))))

;; Using more seq functions
(defn coll-count [coll]
  (reduce (fn [count _] (inc count)) 0 coll))

(= (coll-count '(1 2 3 3 1)) 5)

(= (coll-count "Hello World") 11)

(= (coll-count [[1 2] [3 4] [5 6]]) 3)

(= (coll-count '(13)) 1)

(= (coll-count '(:a :b :c)) 3)


;; 23 Reverse a Sequence

;; Only relying on first/rest
(defn coll-reverse [coll]
  (loop [reverse-coll ()
         coll-to-reverse coll]
      (if (empty? coll-to-reverse)
        reverse-coll
        (recur (cons (first coll-to-reverse) reverse-coll)
               (rest coll-to-reverse)))))

;; Using more seq functions
(defn coll-reverse [coll]
  (reduce conj () coll))

(= (coll-reverse [1 2 3 4 5]) [5 4 3 2 1])

(= (coll-reverse (sorted-set 5 7 2 7)) '(7 5 2))

(= (coll-reverse [[1 2][3 4][5 6]]) [[5 6][3 4][1 2]])


;; 24 Sum It All Up

;; Only relying on first/rest
(defn summing [coll]
  (loop [sum 0
         coll-to-sum coll]
    (if (empty? coll-to-sum)
      sum
      (recur (+ sum (first coll-to-sum)) (rest coll-to-sum)))))

;; Using more seq functions
(defn summing [coll]
  (reduce + coll))

(= (summing [1 2 3]) 6)

(= (summing (list 0 -2 5 5)) 8)

(= (summing #{4 2 1}) 7)

(= (summing '(0 0 -1)) -1)

(= (summing '(1 10 3)) 14)


;; 25 Find the odd numbers

;; Practiced enough loops, now going to try to stick to higher level functions
(defn odds [coll]
  (filter odd? coll))

(= (odds #{1 2 3 4 5}) '(1 3 5))

(= (odds [4 2 1 6]) '(1))

(= (odds [2 2 4 6]) '())

(= (odds [1 1 1 3]) '(1 1 1 3))


;; 26 Fibonacci Sequence

;; First attempt
(defn fibs [n]
  (let [numbers [1 1]]
    (case n
      1 [1]
      2 numbers
      (reduce (fn [fib-numbers, number]
                (conj fib-numbers (+ (last fib-numbers) (nth fib-numbers (- number 3)))))
              numbers
              (range 3 (+ n 1))))))

;; Thinking there must be a better way and learning about lazy-seq's online
(defn fibs [n]
  (take n ((fn fib-seq [x y] (cons x (lazy-seq (fib-seq y (+ x y))))) 1 1)))

(= (fibs 3) '(1 1 2))

(= (fibs 6) '(1 1 2 3 5 8))

(= (fibs 8) '(1 1 2 3 5 8 13 21))


;; 27 Palindrome Detector

(defn palindrome? [coll]
  (= (reverse coll) (map identity coll)))

(false? (palindrome? '(1 2 3 4 5)))

(true? (palindrome? "racecar"))

(true? (palindrome? [:foo :bar :foo]))

(true? (palindrome? '(1 1 3 3 1 1)))

(false? (palindrome? '(:a :b :c)))


;; 28 Flatten a Sequence

(defn coll-flatten [coll]
  (reduce (fn [flattened elem]
            (if (sequential? elem)
              (concat flattened (coll-flatten elem))
              (concat flattened (list elem))))
          ()
          coll))

(= (coll-flatten '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))

(= (coll-flatten ["a" ["b"] "c"]) '("a" "b" "c"))

(= (coll-flatten '((((:a))))) '(:a))
