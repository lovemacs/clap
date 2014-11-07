(ns reader.snake
  (:import (java.awt.event ActionListener)
           (javax.swing JTextArea JScrollPane)
           (java.awt GridLayout))
  (:import (java.util Date Calendar)
           (java.net URI ServerSocket)
           java.sql.DriverManager
           (java.awt Color Dimension)
           (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use examples.import-static))

(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN) ; 左右上下

;;; test
(def *now* (Date.))
(println *now*)



;(proxy [JPanel] []
;  (paintComponent [g]
;    (proxy-super paintComponet g)))


;;; 定义画板宽高
(def width 300)
(def height 300)
(def win-length 5)
(def dirs {VK_LEFT  [-1 0]
           VK_RIGHT [1 0]
           VK_UP    [0 -1]
           VK_DOWN  [0 1]})

(defn fill-point [g location color]
  (.setColor g color)
  (.fillRect g (first location) (second location) 10 10))


(defn create-apple [] {:color (Color. 255 0 0) :type :apple :location [(rand-int width) (rand-int height)]})
(defn paint-apple [g apple] (fill-point g (apple :location) (apple :color)))

(defn create-snake [] {:color (Color. 0 255 0) :type :snake :body (list [1 1]) :dir [1 0]})
(defn paint-snake [g snake] (doseq [point (snake :body)] (fill-point g point (snake :color))))


(defn add-points [pt dir] (map + pt dir))
;;;test
(def value1 (add-points [1 1] [10 0]))
(println "value1" value1)

(defn move-snake-body [snake]
  (assoc snake :body (cons (add-points (first (snake :body)) (snake :dir)) (butlast (snake :body)))))

(defn move-snake [snake]
  ; (def snake (assoc snake :body (cons (add-points (first (snake :body))) (butlast (snake :body))))) ; not work
  (dosync (alter snake move-snake-body))                    ;
  ;(println "snake = " snake)
  )

;;; test
(def asnake (create-snake))
(println "asnake = " asnake)
(def value2 (add-points (first (asnake :body)) [10 0]))
(println "value2 = " value2)



(defn turn [snake newdir] (assoc snake :dir newdir))

(defn update-directions [snake newdir]
 (when newdir (dosync (alter snake turn newdir))) 
)


;;;
(defn game-Panel [apple snake]
  (proxy [JPanel ActionListener KeyListener] []

    (paintComponent [g]    ; 重写JPanel的方法: protected void printComponent(Graphics g)
      (proxy-super paintComponent g)
      (paint-apple g apple)
      (paint-snake g snake))

    (actionPerformed [e]
      ;(println "action performed ..." + e)
      (move-snake snake)
      (.repaint this))

    (keyPressed [e]               ;按下键盘事件
      (println "key pressed _ _ _" (.getKeyCode e))
      (update-directions snake (dirs (.getKeyCode e))))

    (keyReleased [e]
      (println "key released - - -" (.getKeyCode e))) ;void keyReleased(KeyEvent e) //  释放某个键时调用此方法。

    (keyTyped [e]
      (println "key typed ---" (.getKeyCode e))) ;void keyTyped(KeyEvent e) //  键入某个键时调用此方法。
    )
  )

(defn game []
  (let [apple (create-apple)          ;定义苹果
        snake (ref (create-snake))    ;定义蛇
        frame (JFrame. "snake & apple")
        panel (game-Panel apple snake)
        timer (Timer. 75 panel)       ; 每隔75毫秒触发panel的actionPerformed方法
        ]

    (doto panel
      (.setPreferredSize (Dimension. width height))
      (.setFocusable true)
      (.addKeyListener panel))
    (doto frame (.add panel) (.pack)
                (.setVisible true)
                )
    (doto timer (.start))
    ;[frame panel textArea]
    )
  )

;;;test
(game)                                                      ;入口
