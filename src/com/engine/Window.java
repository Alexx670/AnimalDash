package com.engine;

import com.utillity.Constants;
import com.utillity.Time;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable{

    private static Window window = null;
    private boolean isRunning = true;
    public KListener keyListener;
    public MListener mouseListener;
    private Scene currentScene = null;
    private Image dBuffImage = null;
    private Graphics dBuffGraphics = null;

    // konstruktor okna graficznego
    public Window() {
        this.setSize(Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.keyListener = new KListener();
        this.addKeyListener(keyListener);
        this.mouseListener = new MListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);

    }

    // inicjalizacja sceny
    public void init() {
        changeScene(0);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    // funkcja realizująca zmianę scen
    public void changeScene(int scene) {
        switch (scene) {
            case 0:
                currentScene = new StartScene("Start Menu");
                currentScene.init();
                break;
            case 1:
                currentScene = new LevelScene("Level 1 Scene", 1);
                currentScene.init();
                break;
            case 2:
                currentScene = new LevelScene("Level 2 Scene", 2);
                currentScene.init();
                break;
            default:
                System.out.println("Brak sceny");
                currentScene = null;
                break;
        }
    }

    public void draw (Graphics g){
        if (dBuffImage == null) {
            dBuffImage = createImage(getWidth(), getHeight());
            dBuffGraphics = dBuffImage.getGraphics();
        }
        render(dBuffGraphics);
        g.drawImage(dBuffImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void render (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        currentScene.draw(g2);
    }

    public static Window getWindow(){
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void update(double dt) {
        currentScene.update(dt);
        draw(getGraphics());
        System.out.println(dt);
    }

    @Override
    public void run() {
       double lastFrameTime = 0.0;
       try {
           while (isRunning){
               double time = Time.getTime();
               double dt = time - lastFrameTime;
               lastFrameTime = time;

               update(dt);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }


}
