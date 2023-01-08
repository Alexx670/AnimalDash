package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Scene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

/**
 * Klasa służąca do wyświetlania aktualnego wyniku gry.
 * Dziedziczy po klasie Component.
 */
public class Score extends Component {
    int score;  // wyświetlany wynik
    Image text; // wyświetlany przed wynikiem napis: "Wynik:"
    Image[] scoresTable = new Image[(Constants.PROP_COUNT/2)+1];    // tabela z grafikami wyświetlanych cyfr

    /**
     * Konstruktor parametryczny klasy Score
     * @param text ścieżka do pliku z grafiką napisu do wyświetlenia
     * @param scores tabela ze ścieżkami do grafik z cyframi do wyświetlenia
     */
    public Score(String text, String[] scores) {
        this.score = 0;
        this.text = Assets.getImage(text).copy();

        for (int i = 0; i< Constants.PROP_COUNT/2+1; i++) {
            this.scoresTable[i] = Assets.getImage(scores[i]).copy();
        }
    }

    /**
     * Metoda służąca do ustawienia wartości wyniku do wyświetlenia
     * @param score wynik do wyświetlenia
     */
    public void setScore(int score) { this.score = score; }

    /**
     * Metoda odpowiedzialna za aktualizację pozycji wyświetlanego wyniku tak, aby podążał za kamerą
     * (był zawsze wyświetlany w tym samym miejscu okna gry)
     * @param dt czas między kolejnymi wywołaniami funkcji
     */
    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.SCORE_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.SCORE_OFFSET_Y;
    }

    /**
     * Metoda odpowiedzialna za rysowanie wyniku
     * @param g2 grafika okna gry
     */
    public void draw(Graphics2D g2) {
        // narysuj napis "Wynik:"
        g2.drawImage(this.text.image,  (int)this.gameObject.location.position.x, (int)this.gameObject.location.position.y,
                Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
        // narysuj cyfrę odpowiadającą wynikowi
        Image nrDrawn = scoresTable[score];
        g2.drawImage(nrDrawn.image,  (int)this.gameObject.location.position.x + Constants.NUMBER_OFFSET_X,
                (int)this.gameObject.location.position.y, Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
    }

}
