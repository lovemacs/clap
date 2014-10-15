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

(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)

;;; test
(def *now* (Date.))
(println *now*)



;(proxy [JPanel] []
;  (paintComponent [g]
;    (proxy-super paintComponet g)))


;;; 定义画板宽高
(def width 300)
(def height 300)

(defn fill-point [g location color]
  (.setColor g color)
  (.fillRect g (first location) (second location) 10 10))


;;; 定义苹果
( defn create-apple [] {:color (Color. 255 0 0) :type :apple :location [(rand-int width) (rand-int height)]})
;;; 画苹果
(defn paint-apple [g apple] (fill-point g  (apple :location) (apple :color)))

;; 定义蛇
( defn create-snake [] {:color (Color. 0 255 0) :type :snake :body (list [1 1]) :dir [1 0]})
;;; 画蛇
(defn paint-snake [g snake] (doseq [point (snake :body)] (fill-point g point (snake :color))))


(defn add-points [ pt] (map + pt [10 0]))
;;;test
(def value1 (add-points [1 1]))
(println "value1" value1)

(defn move-snake-body [snake]
  (assoc snake :body (cons (add-points (first (snake :body))) (butlast (snake :body)))))

(defn move-snake  [snake]
  ; (def snake (assoc snake :body (cons (add-points (first (snake :body))) (butlast (snake :body))))) ; not work
  (dosync (alter snake move-snake-body))  ;
  (println "snake = " snake)
  )

;;; test
(def asnake (create-snake))
(println "asnake = " asnake)
(def value2 (add-points (first (asnake :body))))
(println "value2 = " value2)






;;;
(defn game-Panel [apple snake]
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (paint-apple g apple)
      (paint-snake g snake)
      )
    (actionPerformed [e]
      (move-snake snake)


      (.repaint this)
      )
    )
  )

(defn game []
  (let [apple (create-apple)
        snake (ref (create-snake))
        frame (JFrame. "snake & apple")
        panel (game-Panel apple snake)
        timer (Timer. 75 panel)
        ]

    (doto panel (.setPreferredSize (Dimension. width height)))
    (doto frame (.add panel) (.pack)
                (.setVisible true)
                )
    (doto timer (.start))
    ;[frame panel textArea]
    )
  )

;;;test
(game)
