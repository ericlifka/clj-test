(ns main
  (:require [clojure.string :as string]))

(defn create-canvas []
  (let [width (aget js/window "innerWidth")
        height (aget js/window "innerHeight")
        canvas (.createElement js/document "canvas")]
    (aset canvas "width" width)
    (aset canvas "height" height)
    (.appendChild js/document.body canvas)
    canvas))

(defn create-world [canvas-element]
  (js-obj
    :width (aget canvas-element "width")
    :height (aget canvas-element "height")
    :rockets []))

(defn random-angle-vector []
  {:x (- .5 (rand))
   :y (+ .5 (rand))})

(defn spawn-random-rocket [game-world]
  (let [rockets (aget game-world :rockets)
        new-rocket {:pos-x (rand-int (aget game-world :width))
                    :pos-y (aget game-world :height)
                    :dir-vector (random-angle-vector)}]
    (aset game-world
      :rockets (conj rockets new-rocket))))

(defn update-rocket-step [rocket]
  (let [dir-vector (get rocket :dir-vector)
        {x-step :x y-step :y} dir-vector]
    {:pos-x (+ x-step (get rocket :pos-x))
     :pos-y (+ y-step (get rocket :pos-y))
     :dir-vector dir-vector}))

(defn update [game-world]
  (aset game-world
    :rockets (map update-rocket-step
               (aget game-world :rockets))))

(defn clear-for-frame [graphics-context]
  (let [canvas (aget graphics-context "canvas")
        height (aget canvas "height")
        width (aget canvas "width")]
    (.clearRect graphics-context 0 0 width height)))

(defn render [graphics-context game-world]
  (doseq [rocket (aget game-world :rockets)]
    (.fillRect graphics-context
      (get rocket :pos-x)
      (get rocket :pos-y)
      5 5)))

(defn ^:export main []
  (let [canvas-element (create-canvas)
        graphics-context (.getContext canvas-element "2d")
        game-world (create-world canvas-element)]
    (aset canvas-element "onclick"
      (fn [] (spawn-random-rocket game-world)))
    (letfn [(anim-frame []
              (update game-world)
              (clear-for-frame graphics-context)
              (render graphics-context game-world)
              (.requestAnimationFrame js/window anim-frame))]
      (anim-frame))))
