package com.engine;

import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

/**
 * Klasa służąca do rysowania i obsługi sceny końcowej gry i menu końcowego gry.
 * Dziedziczy po klasie Scene.
 */
public class EndScene extends Scene {
    private int[] score;    // przechowuje tablicę z wynikami z całej gry

    // tabela z grafikami cyfr
    String[] numbers = {"assets/score_0.png", "assets/score_1.png", "assets/score_2.png", "assets/score_3.png"};

    // pliki z grafikami przycisków i napisów
    String slash = "assets/slash.png";
    String scoreText = "assets/score.png";
    String exit = "assets/exit.png";
    String repeatText = "assets/repeat.png";
    String backgroundImage = "assets/background.png";

    /**
     * Konstruktor parametryczny klasy EndScene.
     * @param name nazwa sceny
     */
    public EndScene(String name) {
        super.Scene(name);
        this.score = Window.getWindow().scores; // przepisuje tablicę wyników z okna gry
    }

    /**
     * Metoda służąca do aktualizacji stanu sceny końcowej - czyli sprawdzenia, czy któryś z przycisków został
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
                // jeśli tak - powtórz grę zaczynając od 1 poziomu
                currentWindow.changeScene(1);
            }
            else {
                // jeśli mysz jest na polu "Wyjdź"
                if ((currentWindow.mouseListener.getMousePosition().x > Constants.LEVEL_END_OFFSET_X + 270) &&
                        (currentWindow.mouseListener.getMousePosition().x < Constants.LEVEL_END_OFFSET_X + 270 + 165) &&
                        (currentWindow.mouseListener.getMousePosition().y >
                                Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y + 25) &&
                        (currentWindow.mouseListener.getMousePosition().y <
                                Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y + Constants.TEXT_HEIGHT + 15)) {
                    // jeśli tak - przejdź przejdź do sceny startowej
                    currentWindow.changeScene(0);
                }
            }
        }
    }

    /**
     * Metoda służąca do rysowania sceny końcowej.
     * @param g2 grafika okna gry
     */
    @Override
    public void draw(Graphics2D g2) {
        // rysuj tło i prostokąt, w którym pojawi się menu końcowe
        g2.drawImage(Assets.getImage(backgroundImage).image, 0, 0, null);   // tło sceny końcowej
        g2.setColor(Color.getHSBColor(100.0f, 100.0f, 100.0f));     // kolor prostokąta, na którym wyświetlone zostanie menu
        g2.fillRect(Constants.LEVEL_END_OFFSET_X, Constants.LEVEL_END_OFFSET_Y, 450, 250);  // rysowanie prostokąta

        // rysuj wynik z 1 poziomu
        g2.drawImage(Assets.getImage(scoreText).image, Constants.LEVEL_END_OFFSET_X + 20, Constants.LEVEL_END_OFFSET_Y, null);
        g2.drawImage(Assets.getImage(numbers[score[0]]).image, Constants.LEVEL_END_OFFSET_X + 200, Constants.LEVEL_END_OFFSET_Y, null);
        g2.drawImage(Assets.getImage(slash).image, Constants.LEVEL_END_OFFSET_X + 230, Constants.LEVEL_END_OFFSET_Y, null);
        g2.drawImage(Assets.getImage(numbers[Constants.LEVEL_MAX_POINTS]).image, Constants.LEVEL_END_OFFSET_X + 260,
                Constants.LEVEL_END_OFFSET_Y, null);

        // rysuj wynik z 2 poziomu
        g2.drawImage(Assets.getImage(scoreText).image, Constants.LEVEL_END_OFFSET_X + 20,
                Constants.LEVEL_END_OFFSET_Y + (int)(0.5*Constants.LEVEL_END_DISTANCE_Y), null);
        g2.drawImage(Assets.getImage(numbers[score[1]]).image, Constants.LEVEL_END_OFFSET_X + 200,
                Constants.LEVEL_END_OFFSET_Y + (int)(0.5*Constants.LEVEL_END_DISTANCE_Y), null);
        g2.drawImage(Assets.getImage(slash).image, Constants.LEVEL_END_OFFSET_X + 230,
                Constants.LEVEL_END_OFFSET_Y + (int)(0.5*Constants.LEVEL_END_DISTANCE_Y), null);
        g2.drawImage(Assets.getImage(numbers[Constants.LEVEL_MAX_POINTS]).image, Constants.LEVEL_END_OFFSET_X + 260,
                Constants.LEVEL_END_OFFSET_Y + (int)(0.5*Constants.LEVEL_END_DISTANCE_Y), null);

        // rysuj przycisk "Powtórz"
        g2.drawImage(Assets.getImage(repeatText).image, Constants.LEVEL_END_OFFSET_X,
                Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y, null);

        // rysuj przycisk "Wyjdź"
        g2.drawImage(Assets.getImage(exit).image, Constants.LEVEL_END_OFFSET_X + 275,
                Constants.LEVEL_END_OFFSET_Y + Constants.LEVEL_END_DISTANCE_Y, null);


    }
}
