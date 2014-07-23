(ns robot-control.robot-consumer
  (:require [physicloud.core :as core]
            [physicloud.task :as t])
  (:import (edu.ycp.robotics KobukiRobot)))


(defn -main []

	(def robot-consumer-cpu (core/cyber-physical-unit "10.10.10.3"))
 
  (def ^KobukiRobot robot (KobukiRobot. "/dev/ttyUSB0"))
  
  (defn base-control 
   "Causes the robot to move at a certain velocity and radius."
   [v, r]
     (.baseControl robot (short v) (short r)))
	
	(core/on-pool t/exec (core/into-physicloud robot-consumer-cpu))
	
	(core/task robot-consumer-cpu {:name "robot-consumer"
	                               :function (fn [this robot-control-data]
	                                           (println "current data being used: "robot-control-data)
                                             (base-control (:linear-velocity robot-control-data) (:angular-velocity robot-control-data)))}))
