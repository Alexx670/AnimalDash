package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

/**
 * Klasa odpowiedzialna za wyświetlanie pytania dla gracza.
 * Dziedziczy po klasie Component.
 */
public class Question extends Component {
    Image question; // grafika tekstu pytania do gracza
    Image cloud;    // grafika chmurki, w której pojawia się pytanie

    /**
     * Konstruktor parametryczny klasy Question
     * @param cloud ścieżka do pliku z obrazkiem chmurki
     * @param question ścieżka do pliku z grafiką z pytaniem
     */
    public Question (String cloud, String question) {
        this.question = Assets.getImage(question).copy();
        this.cloud = Assets.getImage(cloud).copy();
    }

    /**
     * Metoda odpowiedzialna za zmianę treści pytania.
     * @param question grafika z tekstem nowego pytania
     */
    public void setQuestion(Image question) {
        this.question = question.copy();
    }

    /**
     * Metoda odpowiedzialna za aktualizację pozycji wyświetlanego pytania tak, aby podążało za kamerą
     * (było zawsze wyświetlane w tym samym miejscu okna gry)
     * @param dt czas między kolejnymi wywołaniami funkcji
     */
    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.QUESTION_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.QUESTION_OFFSET_Y;
    }

    /**
     * Metoda odpowiedzialna za rysowanie chmurki z pytaniem do gracza.
     * @param g2 grafika okna gry.
     */
    public void draw(Graphics2D g2) {
        // narusyj chmurkę z pytaniem
        g2.drawImage(this.cloud.image, (int) this.gameObject.location.position.x, (int) this.gameObject.location.position.y,
                (int)(this.cloud.width*this.gameObject.location.scale.x), (int)(this.cloud.height*this.gameObject.location.scale.y),null);
        // narysuj treść pytania
        g2.drawImage(this.question.image, (int) this.gameObject.location.position.x, (int) this.gameObject.location.position.y, null);
    }
}
