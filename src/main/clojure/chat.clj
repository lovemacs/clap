;;; 定义消息记录
(defrecord Message [sender text])

;;; 实例化
(user.Message. "Aaron" "Hello")

;;; 消息列表
;(def validate-message-list (partial every? #(and (:sender %) (:text %))))
(def messages (ref ()
                   ;:validator validate-message-list
                   ))

;;; 更新消息,不要这么做
(defn navie-add-message [msg]
  (dosync (ref-set messages (cons msg @messages))))

;;; 更新消息,OK
(defn add-message [msg]
  (dosync (alter messages conj msg)))

;;; 测试-添加几条消息
(add-message (user.Message. "user 1" "hello"))
(add-message "not a valid message")
(add-message (user.Message. "user 2" "howdy"))



