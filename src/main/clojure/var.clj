;;; ����foo
(def ^:dynamic foo 10 )

                                        ;(.start (Thread. (fn [] (println foo))))  ; not work?

(defn print-foo
  "��������: ��ӡfoo"
  []
  (println foo))

;;; test
(binding [foo 42] (print-foo) foo)      ;��ӡ42 ����42
(let [foo 30] (print-foo) foo)          ;��ӡ10 ����30

;;; demo2
;;; ���庯�� slow-double
(defn ^:dynamic slow-double [n] (Thread/sleep 100) (* n 2))

;;; test
(def xs [1 2 1 2 1 2])
(def call-slow-double (map slow-double xs))
;;; 
(time (dorun
       call-slow-double)) 

;;; ��̬��
(time (dorun
       (binding [slow-double (memoize slow-double)]
         call-slow-double)))


