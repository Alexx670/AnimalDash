package com.components;

import com.engine.Component;
import com.engine.GameObject;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

/**
 * Klasa odpowiedzialna za realizację ruchu tła i podłoża.
 * Dziedziczy po klasie Component
 */
public class MovingBackground extends Component {
    private Image backgroundImage;  // rysunek tła
    public int width, height;       // rozmiar rysunku tła
    public int timeStep;    // krok iteracji ruchu tła
    private float speed;    // szybkość ruchu tła
    public GameObject[] backgroundTable;    // tabela wszystkich zapętlonych instancji tła
    float ypos = 0; // zmienna pozwalająca na śledzenie przez tło ruchu pionowego gracza po zniknięciu podłoża z pola widzenia

    private Ground ground;  // referencja do komponentu podłoża
    private boolean isGround;   // czy jest ruchomym podłożem

    /**
     * Konstruktor parametryczny klasy MovingBackground
     * @param file ścieżka do pliku z grafiką
     * @param backgroundTable tabela z tłami
     * @param ground podłoże
     * @param isGround czy jest podłożem
     */
    public MovingBackground(String file, GameObject[] backgroundTable, Ground ground, boolean isGround) {
        this.backgroundImage = Assets.getImage(file).copy();
        this.width = this.backgroundImage.width;
        this.height = this.backgroundImage.height;
        this.backgroundTable = backgroundTable;
        this.ground = ground;
        this.timeStep = 0;
        this.speed = 70.0f; // jeśli jest tłem to porusza się znacznie wolniej niż gracz
        this.isGround = isGround;

        // jeśli jest podłożem porusza się nieznacznie wolniej niż gracz
        if (isGround) {
            this.speed = Constants.X_VELOCITY - 30;
        }
    }

    /**
     * Metoda odpowiedzialna za aktualizację parametrów ruchomego tła/podłoża
     * @param dt odstęp czasu między wywołaniami funkcji
     */
    @Override
    public void update(double dt) {
        this.timeStep += 1;     // zliczanie kroków

        this.gameObject.location.position.x -= dt * speed;  // zmiana pozycji zgodnie z szybkością
        this.gameObject.location.position.x = (float)Math.floor(this.gameObject.location.position.x);   // zaokrglenie w dół do liczby całkowitej

        // jeśli tło zniknie z okna widoku użytkownika
        if (this.gameObject.location.position.x < (-width)) {
            // znajdź tło najbardziej po prawej i ustaw się za nim
            float xMax = 0.0f;
            int otherBackroundTimeStep = 0;
            for (GameObject g : backgroundTable) {
                if(g.location.position.x > xMax) {
                    xMax = g.location.position.x;
                    otherBackroundTimeStep = g.getComponent(MovingBackground.class).timeStep;
                }
            }

            // jeśli oba tła są na tym samym kroku
            if (this.timeStep == otherBackroundTimeStep) {
                // nie trzeba uwzględniać się odstępu czasowego dt między nimi
                this.gameObject.location.position.x = xMax + width;
            }
            else {
                //  trzeba uwzględnić się odstępu czasowego dt między nimi
                this.gameObject.location.position.x = (float)(Math.floor((xMax + width) - (dt * speed)));
            }

            // jeśli poruszający się obiekt to podłoże, to jest zawsze na tej samej wysokości co podłoże
            if (this.isGround) {
                this.gameObject.location.position.y = ground.gameObject.location.position.y;
            }
        }
    }

    /**
     * Metoda odpowiedzialna za rysowanie ruchomego tła/podłoża
     * @param g2 grafika okna gry
     */
    @Override
    public void draw(Graphics2D g2) {
        // jeśli rysuje podłoże
        if (this.isGround) {
            g2.drawImage(this.backgroundImage.image, (int)this.gameObject.location.position.x,
                    (int)(this.gameObject.location.position.y - Window.getWindow().getCurrentScene().camera.position.y),
                    width, height, null);
        } else {
            // rysuje tło

            if (Window.getWindow().getCurrentScene().camera.position.y > -220) {
                g2.drawImage(this.backgroundImage.image, (int) this.gameObject.location.position.x,
                        (int) (this.gameObject.location.position.y - Window.getWindow().getCurrentScene().camera.position.y),
                        width, Constants.SCREEN_HEIGHT, null);
                ypos = Window.getWindow().getCurrentScene().camera.position.y;

            } else {
                g2.drawImage(this.backgroundImage.image, (int) this.gameObject.location.position.x,
                        (int) (this.gameObject.location.position.y - ypos),
                        width, Constants.SCREEN_HEIGHT, null);
            }
        }
    }

    /**
     * Dostęp do szybkości ruchu tła/podłoża
     * @return szybkość ruchu tła/podłoża
     */
    public float getSpeed() {
        return this.speed;
    }

}
