(ns female-and-Male)

;Hofstadter Female and Male sequences
;The Hofstadter Female (F) and Male (M) sequences are defined as follows
;
;F(0) = 1; M(0) = 0
;F(n) = n - M(F(n-1)), n > 0
;M(n) = n - F(M(n-1)), n > 0
;The first few terms of these sequences are
;
;F: 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 8, 8, 9, 9, 10, 11, 11, 12, 13, ... (sequence A005378 in OEIS)
;M: 0, 0, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 9, 9, 10, 11, 11, 12, 12, ... (sequence A005379 in OEIS)

(declare f m)
(defn f [n] (if (zero? n)
              1
              (- n (m (f (dec n))))))
(defn m [n] (if (zero? n)
              0
              (- n (f (m (dec n))))))


(time (m 30))

(def value1 (for [binding (range 30)] (f binding)))
(println "value1 = " value1)

(def value2 (for [binding (range 30)] (m binding)))
(println "value2 = " value2)



;;; Ê¹ÓÃmemoizeº¯Êý
(println ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")

                                        
(def m-memoize (memoize m))
(def f-memoize (memoize f))
(time (m 30))


;;; 
(println ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")

(def m-seq (map m-memoize (iterate inc 0)))
(def f-seq (map f-memoize (iterate inc 0)))
(time (nth m-seq 30))





