(ns main
  (:require [clojure.string :as string]))

(defn make-div [classes html]
  (let [div (js/document.createElement "div")]
    (aset div "innerHTML" html)
    (aset div "className" (string/join " " classes))
    div))

(defn ^:export main [n]
  (let [div (make-div [] "hiya!")]
    (js/document.body.appendChild div)))
