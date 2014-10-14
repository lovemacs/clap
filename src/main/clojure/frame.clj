(ns frame
  (:import (javax.swing JFrame JPanel JTextArea JScrollPane)
           (java.awt GridLayout)))

(def frame (JFrame. "title"))         (.setSize frame 300 300)
(def panel (JPanel.))                  (.setLayout panel (GridLayout.))
(def textArea (JTextArea.))            (.setText textArea  "test")

(.add panel (JScrollPane. textArea))
(.add frame panel)

(.setVisible frame true)