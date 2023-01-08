package com.components;

import com.engine.Component;
import com.engine.Window;
import com.utillity.Constants;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * Klasa odpowiedzialna za określenie obiektu gry jako gracza, rysowanie go oraz realizację skoku.
 * Dziedziczy po klasie Component.
 */
public class Player extends Component {
    Image playerImage;  // obrazek gracza
    private int width, height;  // wysokość i szerokość obrazka gracza
    public boolean canJump;  // czy gracz może wykonać skok

    /**
     * Konstruktor parametryczny klasy Player.
     * @param playerImage obrazek gracza
     */
    public Player(Image playerImage) {
        this.playerImage = playerImage;
        this.width = Constants.PLAYER_WIDTH;
        this.height = Constants.PLAYER_HEIGHT;
        canJump = true;
    }

    /**
     * Metoda odpowiedzialna za skok gracza
     * @param dt czas między kolejnymi wywołaniami metody
     */
    @Override
    public void update(double dt) {
        if (canJump && Window.getWindow().keyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            addJumpVelocity();
            this.canJump = false;
        }
    }

    /**
     * Metoda dodająca graczowi prędkość pionową skoku.
     */
    private void addJumpVelocity() {
        gameObject.getComponent(Physics.class).velocity.y = Constants.JUMP_VELOCITY;
    }

    /**
     * Metoda rysująca gracza.
     * @param g2 grafika okna gry
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(playerImage.image, (int)gameObject.location.position.x, (int)gameObject.location.position.y,
                this.width, this.height, null);
    }
}
