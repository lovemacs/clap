;;; ¼ÆÊıÆ÷
(def counter (ref 0))

(defn next-counter [] (dosync (alter counter inc)))

(next-counter)
(next-counter)
