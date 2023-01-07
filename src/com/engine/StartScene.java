package com.engine;

import com.components.Image;
import com.structure.Assets;
import com.structure.Location;
import com.utillity.Vector2;

import java.awt.Graphics2D;

/**
 * Scena przedstawiająca graczowi startowe menu gry
 */
public class StartScene extends Scene{

    private Image playImage;     // napis "Graj!"
    private Image exitImage;     // napis "Wyjdź"
    private Image backgroundImage;

    /**
     * Konstruktor ustawiający początkowe parametry menu startowego
     * @param name nazwa sceny
     */
    StartScene(String name) {
        super.Scene(name);
        //playImage = Assets.getImage("assets/play.png").copy();
        //exitImage = Assets.getImage("assets/exit.png").copy();
        backgroundImage = Assets.getImage("assets/background.png").copy();
    }

    // inicjalizacja sceny menu startowego
    public void init() {
        // dodanie tła
        GameObject backgroundGameObject = new GameObject("Start Background",
                new Location(new Vector2(0.0f, 0.0f), new Vector2(1.0f, 1.0f)));
        backgroundGameObject.addComponent(backgroundImage);
        addGameObject(backgroundGameObject);

        // dodanie przycisku umożliwiającego rozpoczęcie rozgrywki
        GameObject playGameObject = new GameObject("Play Button",
                new Location(new Vector2(590.0f, 290.0f), new Vector2(1.0f, 1.0f)));
        //playGameObject.addComponent(playImage);
        addGameObject(playGameObject);

        // dodanie przycisku umożliwiającego wyjście z gry
        GameObject exitGameObject = new GameObject("Exit Buton",
                new Location(new Vector2(590.0f, 370.0f), new Vector2(1.0f, 1.0f)));
        //exitGameObject.addComponent(exitImage);
        addGameObject(exitGameObject);
    }

    // metoda odświeżająca obraz menu startowego
    @Override
    public void update(double dt) {
        Window currentWindow = Window.getWindow();

        // jeśli wciśnięto lewy przycisk myszy
        if (currentWindow.mouseListener.getMouseButton()) {
            // jeśli mysz jest na polu "Graj!"
            if (currentWindow.mouseListener.getMousePosition().x > 590.0f && currentWindow.mouseListener.getMousePosition().x < 690.0f &&
                currentWindow.mouseListener.getMousePosition().y > 290.0f && currentWindow.mouseListener.getMousePosition().y < 350.0f) {
                // włącz poziom 1
                currentWindow.changeScene(1);
            }
            else {
                // jeśli mysz jest na polu "Wyjdź"
                if (currentWindow.mouseListener.getMousePosition().x > 590.0f && currentWindow.mouseListener.getMousePosition().x < 690.0f &&
                        currentWindow.mouseListener.getMousePosition().y > 290.0f && currentWindow.mouseListener.getMousePosition().y < 350.0f) {
                    // wyjdź z gry
                    System.exit(0);
                }
            }
        }

        //Window.getWindow().changeScene(1);
    }

    // metod rysująca menu startowe
    @Override
    public void draw(Graphics2D g2) {
        rendering.render(g2);
    }
}
