package com.utillity;

/**
 * Klasa odpowiedzialna za zliczanie czasu.
 */
public class Time {
    public static double timeStarted = System.nanoTime();   // czas zarejestrowany na początku programu.

    /**
     * metoda podająca czas (w sekundach) od włącznenia gry
     * @return czas, jaki upłynął od włączenia gry (w sekundach)
     */
    public static double getTime() {
        return (System.nanoTime() - timeStarted)*1e-9;
    }
}
