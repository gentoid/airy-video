(ns airy-video.core
  (:use [seesaw.core]
        [seesaw.chooser :only [choose-file]])
  (:gen-class))

(def prog-name "Airy Video")

(def prog-version "0.0.1")

(def select-file-btn
  (button
    :id :select-file-btn
    :text "Select file"))

(listen select-file-btn
        :action (fn [e]
                  (choose-file)
                  (config! select-file-btn :enabled? false)))

(def formats-lbl
  (label
    :id ::formats-lbl
    :text "Select output format"))

(def formats-cmb
  (combobox
    :id :formats-cmb
    :model ["AVI", "MPG", "Matroska"]
    :maximum-size [250 :by 40]))

(def left-panel
  (vertical-panel
    :id :left-panel
    :items [select-file-btn
            formats-lbl
            formats-cmb
            :fill-v]))

(def right-panel
  (vertical-panel
    :id :right-panel
    :items [(label :text "Test")]))

(def status-lbl
  (label
    :id :status-lbl
    :text "Status bar"))

(def main-panel
  (vertical-panel
    :id :root
    :minimum-size [50 :by 50]
    :items [(horizontal-panel
              :id :main-container
              :items [left-panel
                      right-panel])
            (horizontal-panel
              :id :status-bar-container
              :items [status-lbl])]))

(def main-window
  (frame
    :id :main-window
    :title (str prog-name " " prog-version)
    :on-close :exit
    :minimum-size [400 :by 150]
    :size [500 :by 200]
    ;:resizable? false
    :content main-panel))

(defn -main
  [& args]
  (invoke-later
    (-> main-window
        pack!
        show!)))
