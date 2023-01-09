package com.engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Klasa odpowiedzialna za odtwarzanie muzyki w tle.
 * Implementuje Runnable.
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
     * @param file ścieżka do pliku z dźwiękiem
     */
    public void playMusic(String file) {
        // spróbuj otworzyć plik z muzyką i ją uruchomić
        try {
            File musicFile = new File(file);    // plik ościeżce podanej do metody

            // jeśli taki plik istnieje
            if (musicFile.exists()) {
                // utwórz z pliku strumień wejściowy audio
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);    // otworzenie strumienia z muzyką
                clip.start();   // rozpoczęcie odtwarzania
                clip.loop(Clip.LOOP_CONTINUOUSLY);  // zapętlanie muzyki
            } else {
                // w przeciwnym wypadku - informacja że nie znaleziono pliku
                System.out.println("File not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        // po uruchomieniu zacznij grać muzykę
        playMusic(musicFilePath);
    }
}
