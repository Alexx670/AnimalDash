package com.engine;

import com.utillity.Constants;
import com.utillity.Time;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable{

    private static Window window = null;    // referencja do okna
    private boolean isRunning = true;   // czy program działa
    public KListener keyListener;   // key listener przypisany do okna
    public MListener mouseListener;     // mouse listener przypisany do okna
    private Scene currentScene = null;      // aktualnie wyświetlana scena
    private Image dBuffImage = null;    // obraz grafiki okna
    private Graphics dBuffGraphics = null;  // grafika okna
    public int[] scores;    // tabela zapamiętanych wyników
    public int currentLevel;    // aktualny poziom

    // konstruktor okna graficznego
    public Window() {
        // wygląd i parametry okna
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // key- and mouse- listeners
        this.keyListener = new KListener();
        this.addKeyListener(keyListener);
        this.mouseListener = new MListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

        // inicjalizacja tabeli wyników
        this.scores = new int[Constants.LEVEL_COUNT];

        // domyślnie ustaw początkowy poziom na 1
        this.currentLevel = 1;

    }

    /**
     * inicjalizacja sceny (początek to menu startowe)
     */
    public void init() {
        changeScene(0);
    }

    /**
     * dostęp do aktualnej sceny
     * @return aktualna scena
     */
    public Scene getCurrentScene() {
        return currentScene;
    }

    /**
     *  funkcja realizująca zmianę scen
     * @param scene scena, na którą chcemy zmienić aktualną scenę
     */
    public void changeScene(int scene) {
        switch (scene) {
            case 0:
                // menu startowe
                currentScene = new StartScene("Start Menu");
                currentScene.init();
                break;
            case 1:
                // poziom 1
                currentScene = new LevelScene("Level 1 Scene", 1);
                currentScene.init();
                break;
            case 2:
                // poziom 2
                currentScene = new LevelScene("Level 2 Scene", 2);
                currentScene.init();
                break;
            case 3:
                // menu końcowe
                currentScene = new EndScene("End Scene");
                currentScene.init();
                break;
            case 4:
                // menu końca poziomu
                currentScene = new LevelEndScene("Level End Scene");
                currentScene.init();
                break;
            default:
                // jeśli podano inny numer sceny
                System.out.println("Brak sceny");
                currentScene = null;
                break;
        }
    }

    /**
     * Funkcja rysująca obraz okna
     * @param g grafika okna
     */
    public void draw (Graphics g){
        if (dBuffImage == null) {
            dBuffImage = createImage(getWidth(), getHeight());
            dBuffGraphics = dBuffImage.getGraphics();
        }
        render(dBuffGraphics);
        g.drawImage(dBuffImage, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * Funkcja renderująca drafikę okna
     * @param g grafika okna
     */
    public void render (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        currentScene.draw(g2);
    }

    /**
     * Dostęp do okna gry
     * @return okno gry
     */
    public static Window getWindow(){
        // jeśli okno nie zostało jeszcze stworzone - utwórz je
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    /**
     * Funkcja aktualizująca stan rysowanych grafik
     * @param dt
     */
    public void update(double dt) {
        currentScene.update(dt);
        draw(getGraphics());
    }

    /**
     * Główna pętla gry
     */
    @Override
    public void run() {
       double lastFrameTime = 0.0;
       try {
           while (isRunning){
               // zliczanie czasu między klatkami (do zachowania zależności czasowych)
               double time = Time.getTime();
               double dt = time - lastFrameTime;
               lastFrameTime = time;

               // aktualizacja parametrów rysowanych obiektów
               update(dt);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }


}
