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

(defn game []
  (let [frame (JFrame. "Title3") panel (JPanel.) textArea (JTextArea.)]
    (doto textArea (.setText "test"))
    (doto panel (.setLayout (GridLayout.)) (.add (JScrollPane. textArea)))
    (doto frame (.setSize 300 300) (.add panel) (.setVisible true))

    ;[frame panel textArea]
    )
  )

;;;test
(game)
