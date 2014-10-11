;;; 定义foo
(def ^:dynamic foo 10 )

                                        ;(.start (Thread. (fn [] (println foo))))  ; not work?

(defn print-foo
  "功能描述: 打印foo"
  []
  (println foo))

;;; test
(binding [foo 42] (print-foo) foo)      ;打印42 返回42
(let [foo 30] (print-foo) foo)          ;打印10 返回30

;;; demo2
;;; 定义函数 slow-double
(defn ^:dynamic slow-double [n] (Thread/sleep 100) (* n 2))

;;; test
(def xs [1 2 1 2 1 2])
(def call-slow-double (map slow-double xs))
;;; 
(time (dorun
       call-slow-double)) 

;;; 动态绑定
(time (dorun
       (binding [slow-double (memoize slow-double)]
         call-slow-double)))


