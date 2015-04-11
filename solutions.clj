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


;; 12 Intro to sequences

(= 3 (first '(3 2 1)))

(= 3 (second [2 3 4]))

(= 3 (last (list 1 2 3)))


;; 13 sequences: rest

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


;; 17 sequences: map

(= '(6 7 8) (map #(+ % 5) '(1 2 3)))


;; 18 sequences: filter

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
(defn penultimate [coll] (second (reverse coll)))

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


;; 29 Get the Caps

(defn cap-getter [s]
  (clojure.string/join (filter #(java.lang.Character/isUpperCase %) s)))

;; Learning about regexes and 'str' from #30

(defn cap-getter [s]
  (apply str (re-seq #"[A-Z]" s)))

(= (cap-getter "HeLlO, WoRlD!") "HLOWRD")

(empty? (cap-getter "nothing"))

(= (cap-getter "$#A(*&987Zf") "AZ")


;; 30 Compress a Sequence

(defn compress [coll]
  (map first (partition-by identity coll)))

(= (apply str (compress "Leeeeeerrroyyy")) "Leroy")

(= (compress [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))

(= (compress [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))


;; 31 Pack a Sequence

(defn pack-a-seq [coll]
  (partition-by identity coll))

(= (pack-a-seq [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))

(= (pack-a-seq [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))

(= (pack-a-seq [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))


;; 32 Duplicate a Sequence

(defn duper [coll]
  (mapcat #(list % %) coll))

(defn duper [coll]
  (interleave coll coll))

(= (duper [1 2 3]) '(1 1 2 2 3 3))

(= (duper [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))

(= (duper [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

(= (duper [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))


;; 33 Replicate a Sequence

(defn replicator [coll n]
  (mapcat (partial repeat n) coll))

(= (replicator [1 2 3] 2) '(1 1 2 2 3 3))

(= (replicator [:a :b] 4) '(:a :a :a :a :b :b :b :b))

(= (replicator [4 5 6] 1) '(4 5 6))

(= (replicator [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))

(= (replicator [44 33] 2) [44 44 33 33])


;; 34 Implement range

;; Lazy sequence
(defn custom-range [start end]
  (when-not (= start end)
    (cons start (lazy-seq (custom-range (inc start) end)))))

;; Less code though...
(defn custom-range [start end]
  (take (- end start) (iterate inc start)))

(= (custom-range 1 4) '(1 2 3))

(= (custom-range -2 2) '(-2 -1 0 1))

(= (custom-range 5 8) '(5 6 7))


;; 35 Local bindings

(= 7 (let [x 5] (+ 2 x)))

(= 7 (let [x 3, y 10] (- y x)))

(= 7 (let [x 21] (let [y 3] (/ x y))))


;; 36 Let it Be

;; Commas for clarity?
(= 10 (let [x 7, y 3, z 1] (+ x y)))

(= 4 (let [x 7, y 3, z 1] (+ y z)))

(= 1 (let [x 7, y 3, z 1] z))


;; 37 Regular Expressions

(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))


;; 38 Maximum value

;; Easy to understand, but likely O(N log N) or worse
(defn max-val [& args]
  (first (sort > args)))

;; Back to O(N)
(defn max-val [f & rest-args]
  (reduce #(if (> %1 %2) %1 %2) f rest-args))

(= (max-val 1 8 3 4) 8)

(= (max-val 30 20) 30)

(= (max-val 45 67 11) 67)


;; 39 Interleave Two Seqs

(defn coll-interleave [coll-1 coll-2]
  (mapcat #(list %1 %2) coll-1 coll-2))

(= (coll-interleave [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))

(= (coll-interleave [1 2] [3 4 5 6]) '(1 3 2 4))

(= (coll-interleave [1 2 3 4] [5]) [1 5])

(= (coll-interleave [30 20] [25 15]) [30 25 20 15])


;; 40 Interpose a Seq

(defn coll-interpose [sep coll]
  (drop-last (interleave coll (repeat sep))))

(= (coll-interpose 0 [1 2 3]) [1 0 2 0 3])

(= (apply str (coll-interpose ", " ["one" "two" "three"])) "one, two, three")

(= (coll-interpose :z [:a :b :c :d]) [:a :z :b :z :c :z :d])


;; 41 Drop Every Nth Item

(defn drop-nth [coll n]
  (keep-indexed #(if (= (inc (mod %1 n)) n) nil %2) coll))

(= (drop-nth [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])

(= (drop-nth [:a :b :c :d :e :f] 2) [:a :c :e])

(= (drop-nth [1 2 3 4 5 6] 4) [1 2 3 5 6])


;; 42 Factorial Fun

(defn fac [n]
  (reduce * (range 1 (inc n))))

(= (fac 1) 1)

(= (fac 3) 6)

(= (fac 5) 120)

(= (fac 8) 40320)


;; 43 Reverse Interleave

;; With help from the Internet
(defn rev-interleave [coll n]
  (apply map list (partition n coll)))

(= (rev-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))

(= (rev-interleave (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))

(= (rev-interleave (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))


;; 44 Rotate Sequence

(defn rotate [n coll]
  (mapcat identity (reverse (split-at (mod n (count coll)) coll))))

(= (rotate 2 [1 2 3 4 5]) '(3 4 5 1 2))

(= (rotate -2 [1 2 3 4 5]) '(4 5 1 2 3))

(= (rotate 6 [1 2 3 4 5]) '(2 3 4 5 1))

(= (rotate 1 '(:a :b :c)) '(:b :c :a))

(= (rotate -4 '(:a :b :c)) '(:c :a :b))


;; 45 Intro to Iterate

(= '(1 4 7 10 13) (take 5 (iterate #(+ 3 %) 1)))


;; 46 Flipping Out

(defn flipping-out [f]
  (fn [& coll] (apply f (reverse coll))))

(= 3 ((flipping-out nth) 2 [1 2 3 4 5]))

(= true ((flipping-out >) 7 8))

(= 4 ((flipping-out quot) 2 8))

(= [1 2 3] ((flipping-out take) [1 2 3 4 5] 3))


;; 47 Contain Yourself

(contains? #{4 5 6} 4)

(contains? [1 1 1 1 1] 4)

(contains? {4 :a 2 :b} 4)

(not (contains? '(1 2 4) 4)) ; works on 4clojure but fails in clojure 1.5.1


;; 48 Intro to some

(= 6 (some #{2 7 6} [5 6 7 8]))

(= 6 (some #(when (even? %) %) [5 6 7 8]))


;; 49 Split a sequence

(defn coll-split-at [n coll]
  (list (take n coll) (drop n coll)))

(= (coll-split-at 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])

(= (coll-split-at 1 [:a :b :c :d]) [[:a] [:b :c :d]])

(= (coll-split-at 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])


;; 50 Split by Type

(defn split-by-type [coll]
  (vals (group-by type coll)))

(= (set (split-by-type [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})

(= (set (split-by-type [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})

(= (set (split-by-type [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})


;; 51 Advanced Destructuring

(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))


;; 52 Intro to Destructuring

(= [2 4] (let [[a b c d e f g] (range)] [c e]))


;; 53 Longest Increasing Sub-Seq

(defn longest-inc-sub-seq [coll]
  (->> (reductions (fn [prev-seq el]
                     (if (< el (first prev-seq))
                       (cons el prev-seq)
                       (list el)))
                   (list (last coll))
                   (rest (reverse coll)))
       (filter #(> (count %) 1))
       (cons ())
       (apply max-key count)))

(= (longest-inc-sub-seq [1 0 1 2 3 0 4 5]) [0 1 2 3])

(= (longest-inc-sub-seq [5 6 1 3 2 7]) [5 6])

(= (longest-inc-sub-seq [2 3 3 4 5]) [3 4 5])

(= (longest-inc-sub-seq [7 6 5 4]) [])


;; 54 Partition a Sequence

(defn coll-partition [n coll]
  (when-not (< (count coll) n)
    (cons (take n coll)
          (lazy-seq (coll-partition n (nthrest coll n))))))

(= (coll-partition 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))

(= (coll-partition 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))

(= (coll-partition 3 (range 8)) '((0 1 2) (3 4 5)))


;; 55 Count Occurrences

(defn coll-frequencies [coll]
  (into {} (map #(vector (first %) (count (second %))) (group-by identity coll))))

;; trying transients for some speeeeed
(defn coll-frequencies [coll]
  (persistent! (reduce (fn [counts el]
                         (assoc! counts el (inc (counts el 0))))
                       (transient {})
                       coll)))

(= (coll-frequencies [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})

(= (coll-frequencies [:b :a :b :a :b]) {:a 2, :b 3})

(= (coll-frequencies '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})


;; 56 Find Distinct Items

(defn coll-distinct [coll]
  (reduce (fn [distincts el]
            (if (some #{el} distincts)
              distincts
              (conj distincts el)))
          []
          coll))

(= (coll-distinct [1 2 1 3 1 2 4]) [1 2 3 4])

(= (coll-distinct [:a :a :b :b :c :c]) [:a :b :c])

(= (coll-distinct '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))

(= (coll-distinct (range 50)) (range 50))


;; 57 Simple Recursion

(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))


;; 58 Function Composition

(defn composition [& fns]
  (fn [& args]
    (first (reduce #(list (apply %2 %1)) args (reverse fns)))))

(= [3 2 1] ((composition rest reverse) [1 2 3 4]))

(= 5 ((composition (partial + 3) second) [1 2 3 4]))

(= true ((composition zero? #(mod % 8) +) 3 5 7 9))

(= "HELLO" ((composition #(.toUpperCase %) #(apply str %) take) 5 "hello world"))


;; 59 Juxtaposition

(defn juxtaposition [& fns]
  (fn [& args]
    (map #(apply % args) fns)))

(= [21 6 1] ((juxtaposition + max min) 2 3 5 1 6 4))

(= ["HELLO" 5] ((juxtaposition #(.toUpperCase %) count) "hello"))

(= [2 6 4] ((juxtaposition :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))


;; 60 Sequence Reductions

(defn coll-reductions
  ([f coll] (coll-reductions f (f (first coll)) (rest coll)))
  ([f initial-val coll]
   (cons initial-val (when (seq coll)
                       (lazy-seq (coll-reductions f
                                                  (f initial-val (first coll))
                                                  (rest coll)))))))

(= (take 5 (coll-reductions + (range))) [0 1 3 6 10])

(= (coll-reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])

(= (last (coll-reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)


;; 61 Map Construction

(defn coll-zipmap [& colls]
  (into {} (apply map vector colls)))

(= (coll-zipmap [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3})

(= (coll-zipmap [1 2 3 4] ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})

(= (coll-zipmap [:foo :bar] ["foo" "bar" "baz"]) {:foo "foo", :bar "bar"})


;; 62 Re-implement Iterate

(defn re-iterate [f x]
  (cons x (lazy-seq (re-iterate f (f x)))))

(= (take 5 (re-iterate #(* 2 %) 1)) [1 2 4 8 16])

(= (take 100 (re-iterate inc 0)) (take 100 (range)))

(= (take 9 (re-iterate #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))


;; 63 Group a Sequence

(defn coll-group-by [f coll]
  (apply merge-with concat (map #(hash-map (f %) (list %)) coll)))

(= (coll-group-by #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]})

(= (coll-group-by #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})

(= (coll-group-by count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})


;; 64 Intro to Reduce

(= 15 (reduce + [1 2 3 4 5]))

(=  0 (reduce + []))

(=  6 (reduce + 1 [2 3]))


;; 65 Black Box Testing

(defn black-box-testing [coll]
  (let [modified-coll (conj (conj coll [:a 1] [:a 1]) [:b 2])
        items-added (- (count modified-coll) (count coll))]
    (if (= items-added 3)
      (if (= (first modified-coll) [:b 2])
        :list
        :vector)
      (if (modified-coll [:a 1])
        :set
        :map))))

(= :map (black-box-testing {:a 1, :b 2}))

(= :list (black-box-testing (range (rand-int 20))))

(= :vector (black-box-testing [1 2 3 4 5 6]))

(= :set (black-box-testing #{10 (rand-int 5)}))

(= [:map :set :vector :list] (map black-box-testing [{} #{} [] ()]))


;; 66 Greatest Common Divisor

(defn gcd [x y]
  (last (filter (fn [divisor]
                  (and (= 0 (rem x divisor)) (= 0 (rem y divisor))))
                (range 1 (inc (min x y))))))

(= (gcd 2 4) 2)

(= (gcd 10 5) 5)

(= (gcd 5 7) 1)

(= (gcd 1023 858) 33)


;; 67 Prime Numbers

(defn- prime? [x]
  (not-any? #(= 0 (rem x %)) (range 2 (inc (Math/floor (Math/sqrt x))))))

(defn- lazy-primes
  ([] (lazy-primes 2))
  ([starting-from]
   (if (prime? starting-from)
     (cons starting-from (lazy-seq (lazy-primes (inc starting-from))))
     (recur (inc starting-from)))))

(defn primes [x]
  (take x (lazy-primes)))

(= (primes 2) [2 3])

(= (primes 5) [2 3 5 7 11])

(= (last (primes 100)) 541)


;; 68 Recurring Theme

(= '(7 6 5 4 3)
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))


;; 69 Merge with a Function

(defn coll-merge-with [f & coll]
  (reduce (fn [merged tuple]
            (assoc merged (first tuple) (if (merged (first tuple))
                                          (f (merged (first tuple)) (second tuple))
                                          (second tuple))))
          (first coll)
          (mapcat (partial into []) (rest coll))))

(= (coll-merge-with * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
   {:a 4, :b 6, :c 20})

(= (coll-merge-with - {1 10, 2 20} {1 3, 2 10, 3 15})
   {1 7, 2 10, 3 15})

(= (coll-merge-with concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
   {:a [3 4 5], :b [6 7], :c [8 9]})


;; 70 Word Sorting

(defn split-sort [s]
  (sort-by #(.toLowerCase %) (re-seq #"\w+" s)))

(= (split-sort  "Have a nice day.")
   ["a" "day" "Have" "nice"])

(= (split-sort  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])

(= (split-sort  "Fools fall for foolish follies.")
   ["fall" "follies" "foolish" "Fools" "for"])


;; 71 Rearranging Code: ->

(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)


;; 72 Rearranging Code: ->>

(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)


;; 73 Analyze a Tic-Tac-Toe Board

(defn left-diag [board]
  (reduce #(cons (nth (nth board %2) %2) %1) () (range 3)))

(defn right-diag [board]
  (left-diag (map reverse board)))

(defn check-for-win [board sym]
  (reduce #(or %1 (apply = sym %2))
          false
          (concat
           (partition 3 (apply interleave board))
           board
           (list (left-diag board) (right-diag board)))))

(defn tic-tac-toe [board]
  (if (check-for-win board :x)
    :x
    (if (check-for-win board :o)
      :o)))


(= nil (tic-tac-toe [[:e :e :e]
                     [:e :e :e]
                     [:e :e :e]]))

(= :x (tic-tac-toe [[:x :e :o]
                    [:x :e :e]
                    [:x :e :o]]))

(= :o (tic-tac-toe [[:e :x :e]
                    [:o :o :o]
                    [:x :e :x]]))

(= nil (tic-tac-toe [[:x :e :o]
                     [:x :x :e]
                     [:o :x :o]]))

(= :x (tic-tac-toe [[:x :e :e]
                    [:o :x :e]
                    [:o :e :x]]))

(= :o (tic-tac-toe [[:x :e :o]
                    [:x :o :e]
                    [:o :e :x]]))

(= nil (tic-tac-toe [[:x :o :x]
                     [:x :o :x]
                     [:o :x :o]]))


;; 74 Filter Perfect Squares

(defn square? [x]
  (let [root (Math/sqrt x)]
    (= (Math/floor root) root)))

(defn filter-squares [s]
  (->> (clojure.string/split s #",")
       (map read-string)
       (filter square?)
       (clojure.string/join ",")))

(= (filter-squares "4,5,6,7,8,9") "4,9")

(= (filter-squares "15,16,25,36,37") "16,25,36")


;; 75 Euler's Totient Function

(defn gcd-copied-from-above [x y]
  (last (filter (fn [divisor]
                  (and (= 0 (rem x divisor)) (= 0 (rem y divisor))))
                (range 1 (inc (min x y))))))

(defn coprime [x y]
  (= 1 (gcd-copied-from-above x y)))

(defn totient [x]
  (inc (count (filter #(coprime %1 x) (range 2 x)))))

(= (totient 1) 1)

(= (totient 10) (count '(1 3 7 9)) 4)

(= (totient 40) 16)

(= (totient 99) 60)


;; 76 Intro to Trampoline

(= [1 3 5 7 9 11]
   (letfn
       [(foo [x y] #(bar (conj x y) y))
        (bar [x y] (if (> (last x) 10)
                     x
                     #(foo x (+ 2 y))))]
     (trampoline foo [] 1)))


;; 77 Anagram Finder

(defn anagrams [coll]
  (set (filter #(> (count %) 1)
               (map #(set (second %))
                    (group-by #(apply str (sort %))
                              coll)))))

(= (anagrams ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}})

(= (anagrams ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})


;; 78 Reimplement Trampoline

(defn re-trampoline [f & args]
  (loop [first-res (apply f args)]
    (if (clojure.test/function? first-res)
      (recur (first-res))
      first-res)))

(= (letfn [(triple [x] #(sub-two (* 3 x)))
           (sub-two [x] #(stop?(- x 2)))
           (stop? [x] (if (> x 50) x #(triple x)))]
     (re-trampoline triple 2))
   82)

(= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
     (map (partial re-trampoline my-even?) (range 6)))
   [true false true false true false])
