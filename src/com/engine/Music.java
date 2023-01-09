package com.engine;


import com.utillity.Time;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Klasa odpowiedzialna za odtwarzanie muzyki w tle.
 * Implementuje Runnable
 */
public class Music implements Runnable {
    String musicFilePath;   // ścieżka do pliku z dźwiękiem

    /**
     * Konstruktor parametryczny klasy Music
     * @param musicFilePath ścieżka do pliku z dźwiękiem
     */
    public Music(String musicFilePath) {
        this.musicFilePath = musicFilePath;
    }

    /**
     * Metoda odpowiedzialna za odtwarzanie muzyki
     * @param file
     */
    public void playMusic(String file) {
        try {
            File musicFile = new File(file);

            if (musicFile.exists()) {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        playMusic(musicFilePath);
    }
}
