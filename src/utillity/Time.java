package utillity;

public class Time {
    public static double timeStarted = System.nanoTime();

    // metoda podająca czas (w sekundach) od włącznenia gry
    public static double getTime() {
        return (System.nanoTime() - timeStarted)*1e-9;
    }
}
