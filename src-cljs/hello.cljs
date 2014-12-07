(ns main
  (:require [clojure.string :as string]))

(defn create-canvas []
  (let [width (aget js/window "innerWidth")
        height (aget js/window "innerHeight")
        canvas (js/document.createElement "canvas")]
    (aset canvas "width" width)
    (aset canvas "height" height)
    (js/document.body.appendChild canvas)
    (.getContext canvas "2d")))

(defn ^:export main [n]
  (let [context (create-canvas)]
    (.fillRect context 10 10 200 200)))
