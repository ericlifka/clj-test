(ns main
  (:require [clojure.string :as string]))

(defn create-canvas []
  (let [width (aget js/window "innerWidth")
        height (aget js/window "innerHeight")
        canvas (.createElement js/document "canvas")]
    (aset canvas "width" width)
    (aset canvas "height" height)
    (.appendChild js/document.body canvas)
    (.getContext canvas "2d")))

(defn render []
  (.log js/console "frame"))

(defn animate []
  (render)
  (.requestAnimationFrame js/window animate))

(defn ^:export main []
  (let [context (create-canvas)]
    (.fillRect context 10 10 200 200)
    (animate)))
