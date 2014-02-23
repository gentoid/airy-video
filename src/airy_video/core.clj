(ns airy-video.core
  (:use [seesaw.core]
        [seesaw.dev]
        [seesaw.chooser :only [choose-file]])
  (:require [me.raynes.fs :as fs])
  (:gen-class))

(def prog-name "Airy Video")

(def prog-version "0.0.1")

(def select-file-btn
  (button
    :id :select-file-btn
    :text "Select file"))

(def selected-file-name
  (label
    :id :selected-file-name
    :minimum-size [200 :by 40]
    :text " "
    :user-data []))

(listen select-file-btn
        :action (fn [e]
                  (when-let [f (choose-file)]
                    (let [file-name (fs/absolute-path f)]
                      (config! selected-file-name
                               :user-data {:file (fs/normalized-path f)
                                           :file-name file-name}
                               :text file-name)))))

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
           selected-file-name
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
