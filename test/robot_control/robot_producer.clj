(ns robot-control.robot-producer
  (use seesaw.core)
  (:require   [physicloud.core :as core]
              [physicloud.task :as t]
              [robot-control.see-saw-wasd-control :as ssc]))
  
(def robot-producer-cpu (core/cyber-physical-unit "10.10.10.3"))

(core/on-pool t/exec (core/into-physicloud robot-producer-cpu ))

(-> ssc/myframe pack!
                show!)

(core/task robot-producer-cpu {:name "producer"
                               :function (fn [this] (ssc/producerfn))
                               :produces "robot-control-data"
                               :update-time 500})