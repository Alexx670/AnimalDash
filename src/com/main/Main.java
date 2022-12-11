package com.main;


import engine.Window;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0"); // wyłączenie skalowania aplikacji przez Windows

        Window window = Window.getWindow();     // utworzenie okna gry
        window.init();      // inicjalizacja okna gry

        Thread mainThread = new Thread(window); // utworzenie głównego wątku gry
        mainThread.start();     // rozpoczęcie głównego wątku gry
    }
}
