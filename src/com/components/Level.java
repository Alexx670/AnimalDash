package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.Graphics2D;

/**
 * Klasa służąca do wyświetlania aktualnego poziomu gry.
 */
public class Level extends Component {
    private int currentLevel;   // zmienna przechowująca aktualny poziom gry
    Image text; // text poziomu "Poziom:"
    Image[] numbersTable = new Image[(Constants.PROP_COUNT/2)+1];   // tabela z cyframi do wyświetlenia

    /**
     * Konstruktor parametryczny klasy Level
     * @param text  ścieżka do pliku z wyświetlanym tekstem
     * @param scores cyfry wyniku możliwe do wyświetlenia
     */
    public Level(String text, String[] scores) {
        this.currentLevel = 0;
        this.text         = Assets.getImage(text).copy();

        // ściąnięcie do tabeli wykorzystywanych grafik
        for (int i = 0; i< Constants.PROP_COUNT/2+1; i++) {
            this.numbersTable[i] = Assets.getImage(scores[i]).copy();
        }
    }

    /**
     * Metoda ustawiająca aktualny poziom wskazywany przez instancję klasy
     * @param currentLevel numer poziomu do wyświetlenia
     */
    public void setCurrentLevel(int currentLevel) {this.currentLevel = currentLevel; }

    /**
     * Metoda odpowiedzialna za aktualizację pozycji wyświetlanego wyniku tak, aby podążał za kamerą
     * (był zawsze wyświetlany w tym samym miejscu okna gry)
     * @param dt czas między kolejnymi wywołaniami funkcji
     */
    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.LEVEL_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.LEVEL_OFFSET_Y;
    }

    /**
     * Metoda rysująca wynik gry
     * @param g2 grafika okna gry
     */
    public void draw(Graphics2D g2) {
        // narysuj napis "Level"
        g2.drawImage(this.text.image,  (int)this.gameObject.location.position.x, (int)this.gameObject.location.position.y,
                Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
        // narysuj cyfrę odpowiadającą poziomowi
        Image nrDrawn = numbersTable[currentLevel];
        g2.drawImage(nrDrawn.image,  (int)this.gameObject.location.position.x + Constants.NUMBER_OFFSET_X,
                (int)this.gameObject.location.position.y, Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
    }

}
