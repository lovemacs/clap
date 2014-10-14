;;; test
(spit "temp.txt" "hello, world1\n" )    ;��,д�ļ�

(spit "temp.txt" "hello, world2\n" :append true) ;���ļ�
(slurp "temp.txt")

;;; ������Ϣ
(def messages (ref ()))

;;; �����ļ�
(def backup-agent (agent "messages-backup.txt"))

;;; �����Ϣ ���� ������ļ�
(defn add-message-with-backup
  "��������: �����Ϣ ���� ������ļ�"
  [msg]
  (dosync
   (let [snapshot (commute messages conj msg)] ;�����Ϣ
     (send-off backup-agent (fn [filename] ;д��Ϣ���ļ�
                             (spit filename snapshot)
                             filename))
     snapshot)))

;;; test
(add-message-with-backup "message1")
(add-message-with-backup "message2")
