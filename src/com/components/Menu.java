package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.Graphics2D;

/**
 * Klasa służąca do wyświetlania napisu "Menu".
 */
public class Menu extends Component {
    Image text; // wyświetlany tekst

    /**
     * Konstruktor parametryczny klasy Menu
     * @param text ścieżka do pliku z wyświetlanym tekstem
     */
    public Menu (String text) {
        this.text = Assets.getImage(text).copy();
    }

    /**
     * Metoda odpowiedzialna za aktualizację pozycji wyświetlanego napisu menu tak, aby podążał za kamerą
     * (był zawsze wyświetlany w tym samym miejscu okna gry)
     * @param dt czas między kolejnymi wywołaniami funkcji
     */
    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.MENU_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.MENU_OFFSET_Y;
    }

    /**
     * Metoda rysująca napis "Menu"
     * @param g2 grafika okna
     */
    public void draw(Graphics2D g2) {
        // narysuj napis "Menu"
        g2.drawImage(this.text.image,  (int)this.gameObject.location.position.x, (int)this.gameObject.location.position.y,
                Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);

    }

}
