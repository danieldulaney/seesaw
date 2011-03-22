(ns seesaw.examples.crazy
  (:use seesaw.core)
  (:import (javax.swing JFrame JLabel)
           (java.awt Color)))

(def rss-url "http://ir.netflix.com/images/toolbar/rss_2.gif")
(def redditor "http://static.reddit.com/reddit.com.header.png")

(defn crazy-app []
  (frame :title "Hello Seesaw" :width 600 :height 400 :pack false :content
    (left-right-split 
      (border-panel 
        :hgap 12 :vgap 15
        :background Color/ORANGE
        :border [10 "Hello there" (empty-border :thickness 15)]
        :north (horizontal-panel 
                :items [(action 
                          #(println "FOO" %) 
                          :name "Click Me"
                          :icon rss-url
                          :tip "Yum!")
                        "<html>Multi-<br><b>LINE</b></html>"
                        :fill-h
                        (toggle 
                          :text "And Me"
                          :on-selection-changed (fn [e] (println (.. (.getSource e) (isSelected))))
                          :icon redditor
                          :tip "Yum!")])
        :center (vertical-panel 
                  :items [(label 
                            :border (line-border) 
                            :text "This label acts like a link" 
                            :on-mouse-clicked (fn [e] (println "CLICK!"))
                            :on-mouse-entered (fn [e] (.. (.getSource e) (setForeground Color/BLUE) ))
                            :on-mouse-exited (fn [e] (.. (.getSource e) (setForeground Color/BLACK) )))
                          (text 
                            :text "HI"
                            :on-action (fn [e] (println (.. (.getSource e) (getText)))))
                          (scrollable 
                            (text 
                              :text (apply str (interpose "\n" (range 0 20))) 
                              :multi-line? true 
                              :editable false))])
        :east  (JLabel. "East")
        :west  (vertical-panel 
                  :background Color/GREEN
                  :border (line-border :color Color/YELLOW :thickness 5) 
                  :items ["A" :fill-v rss-url "C" [:fill-v 45] "D"])
        :south (horizontal-panel 
                :border [(line-border :top 5) (line-border :top 10 :color Color/RED)]
                :items ["A" 
                        :fill-h 
                        "B" 
                        [:fill-h 20] 
                        rss-url 
                        "C"
                        (checkbox 
                          :text "Check me"
                          :on-selection-changed (fn [e] (println (.. (.getSource e) (isSelected)))))]))
    (grid-panel 
      :border [10 "Here's a grid" 10]
      :hgap 10 
      :vgap 10 
      :columns 3 
      :items (map #(action (fn [e] (println "Clicked" %)) :name %) (range 0 12))))))

;(doseq [f (JFrame/getFrames)]
  ;(.dispose f))
;(invoke-later crazy-app)
