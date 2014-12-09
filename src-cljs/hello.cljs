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

(defn spawn-random-rocket [game-world]
  (let [rockets (aget game-world :rockets)
        new-rocket {:pos-x (rand-int (aget game-world :width))
                    :pos-y (aget game-world :height)
                    :angle (- 45 (rand-int 90))}]
    (aset game-world
      :rockets (conj rockets new-rocket))))

(defn update [game-world]
  (aset game-world
    :rockets (map
               (fn [r]
                 {:pos-x (get r :pos-x)
                  :pos-y (- (get r :pos-y) 1)
                  :angle (get r :angle)})
               (aget game-world :rockets))))

(defn clear-for-frame [graphics-context]
  (let [canvas (aget graphics-context "canvas")
        height (aget canvas "height")
        width (aget canvas "width")]
    (.clearRect graphics-context 0 0 width height)))

(defn render [graphics-context]
  (.fillRect graphics-context 10 20 200 100))

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
