(ns reader.snake
  (:import (java.util Date Calendar)
           (java.net URI ServerSocket)
           java.sql.DriverManager)
  (:use examples.import-static)
  )


(def *now* (Date.))
(println *now*)

