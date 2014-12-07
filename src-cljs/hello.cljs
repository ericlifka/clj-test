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

(defn clear-for-frame [graphics-context]
  (let [canvas (aget graphics-context "canvas")
        width (aget canvas "width")
        height (aget canvas "height")]
    (.clearRect graphics-context 0 0 width height)))

(defn render [graphics-context]
  (clear-for-frame graphics-context)
  (.fillRect graphics-context 10 20 200 100))

(defn ^:export main []
  (let [graphics-context (create-canvas)]
    (letfn [(anim-frame []
              (render graphics-context)
              (.requestAnimationFrame js/window anim-frame))] m .
      (anim-frame))))
