;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; 定义
(def current-track (atom "Venus, the bringer of Peace"))
(def current-composer (atom "古斯塔夫·霍尔斯特"))
;;; test
(println @current-track)

;;; 重置
(reset! current-track "Credoy-信条")
;;; test
(println @current-track)

;;; Q: 协同更新? Q: 不可以
(dosync                                 ;不要这么写
 (reset! current-track "ttttt")
 (reset! current-composer "ccccc"))
;;; test
(println @current-track @current-composer)

















;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(println ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;; - ;;; 循环jack的歌" )

;;; 定义记录
         
(def current-track (atom {:title "music 1" :singer "Jack"}))

;;; 测试
(println @current-track)

;;; 连续收听同一个歌手
(swap! current-track assoc :title "music 2")
(println @current-track)

;;; 修改
(reset! current-track {:title "music 3" :singer "XuSong"})
(println @current-track)
