(ns main)

(defn ^:export main [n]
  (let [div (.createElement js/document "div")]
    (aset div "innerHTML" "hiya!")
    (.appendChild js/document.body div)))
