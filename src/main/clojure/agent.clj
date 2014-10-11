;;; test
(spit "temp.txt" "hello, world1\n" )    ;吐,写文件

(spit "temp.txt" "hello, world2\n" :append true) ;读文件
(slurp "temp.txt")

;;; 定义信息
(def messages (ref ()))

;;; 定义文件
(def backup-agent (agent "messages-backup.txt"))

;;; 添加信息 并且 保存进文件
(defn add-message-with-backup
  "功能描述: 添加信息 并且 保存进文件"
  [msg]
  (dosync
   (let [snapshot (commute messages conj msg)] ;添加消息
     (send-off backup-agent (fn [filename] ;写消息到文件
                             (spit filename snapshot)
                             filename))
     snapshot)))

;;; test
(add-message-with-backup "message1")
(add-message-with-backup "message2")
