;; by HanLiangYuan
;; 2014.10.14

(ns frame
  (:import (javax.swing JFrame JPanel JTextArea JScrollPane)
           (java.awt GridLayout)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; 初始
;(def frame (JFrame. "title2"))         (.setSize frame 300 300)
;(def panel (JPanel.))                  (.setLayout panel (GridLayout.))
;(def textArea (JTextArea.))            (.setText textArea  "test")
;(.add panel (JScrollPane. textArea))
;(.add frame panel)
;(.setVisible frame true)

;;; 修改
;(def frame (JFrame. "Title2"))
;(def panel (JPanel.))
;(def textArea (doto (JTextArea.) (.setText "test")) )
;(doto panel (.setLayout (GridLayout.)) (.add (JScrollPane. textArea)))
;(doto frame (.setSize 300 300) (.add panel) (.setVisible true))

;;; 修改
;(defn game []
  (let [frame (JFrame. "Title3")
        panel (JPanel.)
        textArea (JTextArea.)
        ]
    (doto textArea
      (.setText "test"))
    (doto panel
      (.setLayout (GridLayout.))
      (.add (JScrollPane. textArea)))
    (doto frame
      (.setSize 300 300)
      (.add panel)
      (.setVisible true))

    ;[frame panel textArea]
    )
  ;)
