(ns main)

(defn ^:export main [n]
  (let [div (js/document.createElement "div")]
    (aset div "innerHTML" "hiya!")
    (js/document.body.appendChild div)))
