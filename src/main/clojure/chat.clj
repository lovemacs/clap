;;; ������Ϣ��¼
(defrecord Message [sender text])

;;; ʵ����
(user.Message. "Aaron" "Hello")

;;; ��Ϣ�б�
;(def validate-message-list (partial every? #(and (:sender %) (:text %))))
(def messages (ref ()
                   ;:validator validate-message-list
                   ))

;;; ������Ϣ,��Ҫ��ô��
(defn navie-add-message [msg]
  (dosync (ref-set messages (cons msg @messages))))

;;; ������Ϣ,OK
(defn add-message [msg]
  (dosync (alter messages conj msg)))

;;; ����-��Ӽ�����Ϣ
(add-message (user.Message. "user 1" "hello"))
(add-message "not a valid message")
(add-message (user.Message. "user 2" "howdy"))



