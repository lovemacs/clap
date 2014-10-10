 (ns #134)

; #134 A nil key
;输入key map

#(= true (= nil (%2 %1)) (contains? %2 %1))

;(true?  (__ :a {:a nil :b 2}))
;(false? (__ :b {:a nil :b 2}))
;(false? (__ :c {:a nil :b 2}))


