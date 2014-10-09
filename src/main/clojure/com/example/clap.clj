(ns com.example.clap)

(defn main [args]
  (println "Hello World!")
  (println "Java main called clojure function with args: "
           (apply str (interpose " " args))))



