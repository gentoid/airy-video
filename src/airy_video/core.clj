(ns airy-video.core
  (:use [seesaw.core]
        [seesaw.chooser :only [choose-file]])
  (:gen-class))

(def prog-name "Airy Video")

(def prog-version "0.0.1")

(def select-file-btn
  (button
    :text "Select file"
    :minimum-size [150 :by 20]
    :maximum-size [60 :by 20]
    :valign :center
    :halign :center))

(listen select-file-btn
        :action (fn [e]
                  (choose-file)
                  (config! select-file-btn :enabled? false)))

(def formats-lbl (label "Select output format"))
(def formats-cmb (combobox :model ["AVI", "MPG", "Matroska"]))

(def main-panel
  (grid-panel
    :id main-panel
    :items [select-file-btn
            [:fill-v 250]
            formats-lbl
            formats-cmb]))

(def main-window
  (frame
    :id :main-window
    :title (str prog-name " " prog-version)
    :on-close :exit
    :size [610 :by 330]
    :resizable? false
    :content main-panel))

(defn -main
  [& args]
  (invoke-later
    (-> main-window
        show!)))
