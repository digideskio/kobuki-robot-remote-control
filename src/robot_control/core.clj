(ns robot-control.core

(use seesaw.core))

(def myframe (frame :title "Hello" 
                    :on-close :exit
                    :content (label :text "use WASD keys to specify direction")
                    :listen [:key-pressed (fn[e](println "yay!"))]))
(defn -main [x]
(-> myframe pack!
                show!))
