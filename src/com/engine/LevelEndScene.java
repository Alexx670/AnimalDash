package com.engine;

import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

/**
 * Klasa reprezentująca scenę menu pokazującego się po zakończeniu poziomu.
 * Dziedziczy po klasie Scene.
 */
public class LevelEndScene extends Scene {
    private int score;  // wynik z danego poziomu

    // tabela z grafikami cyfr
    String[] numbers = {"assets/score_0.png", "assets/score_1.png", "assets/score_2.png", "assets/score_3.png"};
    String slash = "assets/slash.png";
    String scoreText = "assets/score.png";
    String nextText = "assets/next.png";
    String repeatText = "assets/repeat.png";
    String backgroundImage = "assets/background.png";

    /**
     * Konstruktor parametryczny klasy LevelEndScene.
     * @param name nazwa sceny
     */
    public LevelEndScene(String name) {
        super.Scene(name);
        this.score = Window.getWindow().scores[Window.getWindow().currentLevel - 1];    // przepisanie wyniku z poziomu
    }

    /**
     * Metoda służąca do aktualizacji stanu sceny końca poziomu - czyli sprawdzenia, czy któryś z przycisków został
     * naciśnięty, oraz stosownego zareagowania na taką akcję.
     * @param dt czas, który upłynął między kolejnymi wywołaniami metody
     */
    @Override
    public void update(double dt) {
        Window currentWindow = Window.getWindow();  // zaczep do okna gry w celu oproszczenia zapisu warunków

        // jeśli wciśnięto lewy przycisk myszy
        if (currentWindow.mouseListener.getMouseButton()) {
            // ustaw flagę wciśnięcia przycisku na false (zapobiega podwójnym kliknięciom)
            currentWindow.mouseListener.setMouseButton(false);

            // jeśli mysz jest na polu "Powtórz"
            if ((currentWindow.mouseListener.getMousePosition().x > Constants.LEVEL_END_OFFSET_X + 20) &&
                    (currentWindow.mouseListener.getMousePosition().x < Constants.LEVEL_END_OFFSET_X + 230) &&
                    (currentWindow.mouseListener.getMousePosition().y >
                            Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y + 25) &&
                    (currentWindow.mouseListener.getMousePosition().y <
                            Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y + Constants.TEXT_HEIGHT + 15)) {
                // jeśli tak - powtórz poziom
                currentWindow.changeScene(currentWindow.currentLevel);
            }
            else {
                if (score >= (int)(Constants.LEVEL_MAX_POINTS*0.8)) {
                    if ((currentWindow.mouseListener.getMousePosition().x > Constants.LEVEL_END_OFFSET_X + 290) &&
                            (currentWindow.mouseListener.getMousePosition().x < Constants.LEVEL_END_OFFSET_X + 290 + 135) &&
                            (currentWindow.mouseListener.getMousePosition().y >
                                    Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y + 25) &&
                            (currentWindow.mouseListener.getMousePosition().y <
                                    Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y + Constants.TEXT_HEIGHT + 15)) {
                        // jeśli tak - przejdź do kolejnego poziomu
                        currentWindow.changeScene(currentWindow.currentLevel + 1);
                    }
                }
            }
        }
    }

    /**
     * Metoda służąca do rysowania sceny końca poziomu..
     * @param g2 grafika okna gry
     */
    @Override
    public void draw(Graphics2D g2) {
        // rysuj tło i prostokąt, w którym pojawi się menu końcowe
        g2.drawImage(Assets.getImage(backgroundImage).image, 0, 0, null);
        g2.setColor(Color.getHSBColor(100.0f, 100.0f, 100.0f));
        g2.fillRect(Constants.LEVEL_END_OFFSET_X, Constants.LEVEL_END_OFFSET_Y, 450, 250);

        // rysuj wynik gracza
        g2.drawImage(Assets.getImage(scoreText).image, Constants.LEVEL_END_OFFSET_X + 20, Constants.LEVEL_END_OFFSET_Y, null);
        g2.drawImage(Assets.getImage(numbers[score]).image, Constants.LEVEL_END_OFFSET_X + 200, Constants.LEVEL_END_OFFSET_Y, null);
        g2.drawImage(Assets.getImage(slash).image, Constants.LEVEL_END_OFFSET_X + 230, Constants.LEVEL_END_OFFSET_Y, null);
        g2.drawImage(Assets.getImage(numbers[Constants.LEVEL_MAX_POINTS]).image, Constants.LEVEL_END_OFFSET_X + 260,
                Constants.LEVEL_END_OFFSET_Y, null);

        // rysuj przycisk "Powtórz"
        g2.drawImage(Assets.getImage(repeatText).image, Constants.LEVEL_END_OFFSET_X,
                Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y, null);

        // rysuj przycisk "Dalej" tylko wtedy, kiedy gracz osiągnie minimalny akceptowany wynik
        if (score >= (int)(Constants.LEVEL_MAX_POINTS*0.8)) {
            g2.drawImage(Assets.getImage(nextText).image, Constants.LEVEL_END_OFFSET_X + 230,
                    Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y, null);
        }

    }
}
