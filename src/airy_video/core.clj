(ns airy-video.core
  (:use [seesaw.core]
        [seesaw.chooser :only [choose-file]])
  (:gen-class))

(def prog-name "Airy Video")

(def prog-version "0.0.1")

(def select-file-btn
  (button :text "Select file"))

(listen select-file-btn :action (fn [e]
                                  (choose-file)
                                  (config! select-file-btn :enabled? false)))

(def formats-lbl (label "Select output format"))
(def formats-cmb (combobox :model ["AVI", "MPG", "Matroska"]))

(defn -main
  [& args]
  (invoke-later
    (-> (frame
          :title (str prog-name " " prog-version)
          :on-close :exit
          :content (vertical-panel :items [select-file-btn
                                           formats-lbl
                                           formats-cmb]))
        pack!
        show!)))
