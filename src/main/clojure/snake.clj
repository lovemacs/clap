(ns reader.snake
  (:import (java.awt.event ActionListener)
           (javax.swing JTextArea JScrollPane)
           (java.awt GridLayout))
  (:import  java.io.File
            javax.imageio.ImageIO
           (java.util Date Calendar)
           (java.net URI ServerSocket)
            java.sql.DriverManager
           (java.awt Color Dimension)
           (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use examples.import-static)
  (:use clojure.java.io)
  )


(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN) ; 左右上下

;;; test
(def *now* (Date.))
(println *now*)



;(proxy [JPanel] []
;  (paintComponent [g]
;    (proxy-super paintComponet g)))


;;; 定义画板宽高
(def width 30)
(def height 30)
(def win-length 5)
(def dirs {
            VK_LEFT  [-1 0]
            VK_RIGHT [1 0]
            VK_UP    [0 -1]
            VK_DOWN  [0 1]})

;(def image (ImageIO/read (File. "examples/clojure-icon.gif")))

(defn fill-point-apple [g location color image panel]
  ;(.setColor g color)
  ;(.fillRect g (* 10 (first location)) (* 10 (second location)) 10 10)
  (.drawImage g image (* 10 (first location)) (* 10 (second location)), 15, 15,  panel )
  )


(defn fill-point [g location color]
  (.setColor g color)
  (.fillRect g (* 10 (first location)) (* 10 (second location)) 10 10)
  (.setColor g (Color. 0 0 255))
  (.drawRect g (* 10 (first location)) (* 10 (second location)) 10 10)
  )

(defn create-apple [] {
                        :color    (Color. 255 0 0)
                        :type     :apple
                        :location [(rand-int width) (rand-int height)]
                        :image (ImageIO/read (as-file "examples/clojure-icon.gif"))
                        })

(defn paint-apple [g apple panel] (fill-point-apple g (apple :location) (apple :color) (apple :image) panel))

(defn create-snake [] {
                        :color (Color. 0 255 0)
                        :type  :snake
                        :body  (list [1 1])
                        :dir   [1 0]})

(defn paint-snake [g snake] (doseq [point (snake :body)] (fill-point g point (snake :color))))

(defn add-points [pt dir] (map + pt dir))
;;;test
(def value1 (add-points [1 1] [10 0]))
(println "value1" value1)

;(defn move [snake & grow]                                   ; move snake body
;  (assoc snake :body (cons (add-points (first (snake :body)) (snake :dir)) (if grow (snake :body) (butlast (snake :body))))))

(defn move [{:keys [body dir] :as snake} & grow]
  (let [x (assoc snake :body (cons (add-points (first body) dir)
                                   (if grow body (butlast body))))]
    println "x = " x
    )

  )

;;; test
(def asnake (create-snake))
(println "asnake = " asnake)
(def value2 (add-points (first (asnake :body)) [10 0]))
(println "value2 = " value2)

(defn turn [snake newdir] (assoc snake :dir newdir))        ; 修改snake的:dir

(defn update-directions [snake newdir]
  (when newdir (dosync (alter snake turn newdir)))
  )

(defn abs [a b] (max (- a b) (- b a)))                      ; 求绝对值

;
;(defn eats? [{[snake-head] :body} {[apple-location] :location}]
(defn eats? [{[[snake-head-x snake-head-y]] :body} {[apple-location-x apple-location-y] :location}]
  ;  (println "snake-head: " [snake-head-x snake-head-y] ",apple-location: " [apple-location-x apple-location-y])
  ;  (= snake-head apple-location)
  (and (<= (abs snake-head-x apple-location-x) 1) (<= (abs snake-head-y apple-location-y) 1))
  )



(defn head-overlaps-body?  [{[head & body] :body}]
  (contains? (set body) head))
(def lose? head-overlaps-body?)


(defn  reset-game [snake apple]
  ( dosync
      (ref-set apple (create-apple))
      (ref-set snake (create-snake))
    ))
;;;------------------------------
;;; gui
;;;------------------------------

;;;
(defn game-Panel [apple snake frame]
  (proxy [JPanel ActionListener KeyListener] []

    (paintComponent [g]                                     ; 重写JPanel的方法: protected void printComponent(Graphics g)
      (proxy-super paintComponent g)
      (paint-apple g apple this)
      (paint-snake g snake))

    (actionPerformed [e]
      ; (println "action performed ..." + e)
      ;(if (eats? snake apple) (println "eat: true") (println "eat: false"))
      (dosync
        (if (eats? @snake @apple)
          (
            (do (ref-set apple (create-apple)))
            (let [x (alter snake move :grow)] (println "eats? true: grow" (x :body)))
            )
          (let [x (alter snake move)] (println "eats? false:" (x :body)))
          )
        )
      (when (lose? @snake)
        (reset-game snake apple)
        (JOptionPane/showMessageDialog frame "You lose :(")
        )
      (.repaint this)
      )

    (keyPressed [e]                                         ;按下键盘事件
      ;(println "key pressed _ _ _" (.getKeyCode e))
      (update-directions snake (dirs (.getKeyCode e)))
      )

    (keyReleased [e]
      ;(println "key released - - -" (.getKeyCode e))
      )                                                     ;void keyReleased(KeyEvent e) //  释放某个键时调用此方法。


    (keyTyped [e]
      ;(println "key typed ---" (.getKeyCode e))
      )                                                     ;void keyTyped(KeyEvent e) //  键入某个键时调用此方法。

    ))

(defn game []
  (let [apple (ref (create-apple))                          ;定义苹果
        snake (ref (create-snake))                          ;定义蛇
        frame (JFrame. "snake & apple")
        panel (game-Panel apple snake frame)
        timer (Timer. 75 panel)                             ; 每隔75毫秒触发panel的actionPerformed方法
        ]

    (doto panel
      (.setPreferredSize (Dimension. (* 10 width) (* 10 height)))
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