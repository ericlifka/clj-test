(ns main
  (:require [clojure.string :as string]))

(defn make-div [classes html]
  (let [div (js/document.createElement "div")]
    (aset div "innerHTML" html)
    (aset div "className" (string/join " " classes))
    div))

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
    true))
