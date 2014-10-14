(ns reader.snake
  (:import (java.awt.event ActionListener))
  (:import (java.util Date Calendar)
           (java.net URI ServerSocket)
           java.sql.DriverManager
           (java.awt Color Dimension)
           (javax.swing JPanel JFrame Timer JOptionPane)
           (java.awt.event ActionListener KeyListener))
  (:use examples.import-static))
(import-static java.awt.event.KeyEvent VK_LEFT VK_RIGHT VK_UP VK_DOWN)


(def *now* (Date.))
(println *now*)

(def frame (JFrame. "xxx"))
(.setSize frame 400 400)
(.setVisible frame true)


;(proxy [JPanel] []
;  (paintComponent [g]
;    (proxy-super paintComponet g)))


