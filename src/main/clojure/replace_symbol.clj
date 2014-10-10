(ns replace-symbol)

(println "hello, app01")

(binding [*print-level* 25]

  (defn deeply-nested
    "功能描述: 给xs嵌套n层圆括号"
    [xs n] (if (zero? n)
             (list xs)
             (deeply-nested (list xs) (dec n))))
  ; 测试
  (def value1 (deeply-nested 'iamsymbol 10))
  (println "value1 = " value1)                              ;  (((((((((((iamsymbol)))))))))))


  ; 过于直白的移植,勿用
  (declare replace-symbol replace-symbol-expression)        ;交换递归

  (defn replace-symbol
    "功能描述: 交换递归-全局替换"
    [coll old-symbol new-symbol]
    (if (empty? coll)
      ()
      (cons (replace-symbol-expression (first coll) old-symbol new-symbol)
            (replace-symbol (rest coll) old-symbol new-symbol)))
    )

  (defn replace-symbol-expression
    "功能描述: 交换递归-仅替换一个"
    [first-element old-symbol new-symbol]
    (if (symbol? first-element)                             ;(do (println "  debug: first-element = " first-element) (symbol? first-element))
      (if (= first-element old-symbol)
        new-symbol
        old-symbol)
      (replace-symbol first-element old-symbol new-symbol))
    )

  ; 测试
  (def value2 (replace-symbol value1 'iamsymbol 'newsymbol))
  (println "value2 = " value2)



  ;(set! *print-level* 25)
  ;(def value3 (deeply-nested 'iamsymbol 10000))     ;StackOverflowError
  ;(println "value3 = " value3)
  ;(def value4 (replace-symbol value3'iamsymbol 'newsymbol))
  ;(println "value4 = " value4)


  (defn deeply-nested-iamsymbol
    "功能描述: 给 'iamsymbol 嵌套n层圆括号"
    [n]
    (loop [n n
           result '(iamsymbol)]
      (if (= n 0)
        result
        (recur (dec n) (list result)))
      )
    )

  ; 测试
  ;(set! *print-level* 25)
  (def value3 (deeply-nested-iamsymbol 10000))



  (println "value3 = " value3)                              ;value3 =  (((((((((((((((((((((((((#)))))))))))))))))))))))))
  ;(def value4 (replace-symbol value3 'iamsymbol 'newsymbol))         ;StackOverflowError
  ;(println "value4 = " value4)


  ; 改进: lazy-seq
  (declare replace-symbol replace-symbol-expression)        ;交换递归

  (defn replace-symbol
    "功能描述: 交换递归-全局替换"
    [coll old-symbol new-symbol]
    (if (empty? coll)
      ()
      (lazy-seq (cons (replace-symbol-expression (first coll) old-symbol new-symbol)
                      (replace-symbol (rest coll) old-symbol new-symbol))))
    )

  (defn replace-symbol-expression
    "功能描述: 交换递归-仅替换一个"
    [first-element old-symbol new-symbol]
    (if (symbol? first-element)                             ;(do (println "  debug: first-element = " first-element) (symbol? first-element))
      (if (= first-element old-symbol)
        new-symbol
        old-symbol)
      (replace-symbol first-element old-symbol new-symbol))
    )
  ; 测试
  (def value4 (replace-symbol value3 'iamsymbol 'newsymbol))
  (println "value4 = " value4)                              ;value4 =  (((((((((((((((((((((((((#)))))))))))))))))))))))))


  ; 改进: clojure味更浓
  (defn- coll-or-simbal [x & _] (if (coll? x)
                                  :coll
                                  :symbol))
  (defmulti replace-symbol
            "定义多重方法"
            coll-or-simbal)
  (defmethod replace-symbol :coll [coll old-symbol new-symbol] ;面相列表
    (lazy-seq
      (when (seq coll)
        (cons (replace-symbol (first coll) old-symbol new-symbol) (replace-symbol (rest coll) old-symbol new-symbol)))))
  (defmethod replace-symbol :symbol [symbol old-symbol new-symbol] ; 面相符号
    (if (= symbol old-symbol)
      new-symbol
      old-symbol))

  ; 测试
  (def value4 (replace-symbol value1 'iamsymbol 'newsymbol))
  (println "value4 = " value4)                              ;value4 =  (((((((((((newsymbol)))))))))))
  (def value5 (replace-symbol value3 'iamsymbol 'newsymbol))
  (println "value5 = " value5)                              ;value4 =  (((((((((((newsymbol)))))))))))

  )