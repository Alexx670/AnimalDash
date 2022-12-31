package com.components;

import com.engine.Component;
import com.engine.GameObject;
import com.utillity.Constants;
import com.utillity.Vector2;

public class Box extends Component {

    private float width;
    private float height;
    private float halfWidth;
    private float halfHeight;
    private Vector2 center;
    private boolean transparent;    // czy gracz może przenikać przez ten Box
    private boolean collided;       // czy gracz miał kolizję z tym przedmiotem (tylko jeśli transparent == true)
    private boolean goodProp;   // czy przedmiot funkcyjny (jeśli Box nim jest) jest prawidłową odpowiedzią

    public Box(float width, float height) {
        this.width = width;
        this.height = height;
        this.halfWidth = width / 2.0f;
        this.halfHeight = height / 2.0f;
        this.center = new Vector2();
        this.transparent = false;
        this.collided = false;
        this.goodProp = false;
    }

    @Override
    public void start() {
        this.calculateCenter();
    }

    @Override
    public void update(double dt) {

    }

    /**
     * Obliczenie środka obiektu Box.
     */
    public void calculateCenter() {
        this.center.x = this.gameObject.location.position.x + this.halfWidth;
        this.center.y = this.gameObject.location.position.y + this.halfHeight;
    }

    public float getWidth() { return width; }

    public float getHeight() { return height; }

    public void setTransparent(boolean trans) { this.transparent = trans; }

    public boolean getCollided() { return this.collided; }

    public void setGoodProp(boolean good) { this.goodProp = good; }

    /**
     * Metoda sprawdzająca, czy dochodzi do kolizji między dwoma obiektami.
     * @param b1 - Component Box należący do pierwszego obiektu
     * @param b2 - Component Box należący do drubiego obiektu
     * @return - wartość true lub false wskazująca, czy dochodzi do kolizji
     *
     * Zasada działania:
     *      Jeśli zsumowane połówki szerokości i wysokości boxów b1 i b2 są większe niż odległości między nimi
     *      na osiach x i y, to znaczy że dochodzi do kolizji.
     */
    public static boolean checkCollision (Box b1, Box b2) {
        // obliczenie aktualnych środków obiektów
        b1.calculateCenter();
        b2.calculateCenter();

        float dx, dy;   // odległości między środkami b1 i b2 na osiach x i y
        dx = b2.center.x - b1.center.x;
        dy = b2.center.y - b1.center.y;

        float addedHalfWidths, addedHalfHeights;    // dodane do siebie połowy szerokości i długości b1 i b2
        addedHalfWidths = b1.halfWidth + b2.halfWidth;
        addedHalfHeights = b1.halfHeight + b2.halfHeight;

        // sprawdzenie warunku kolizji
        return (Math.abs(dx) <= addedHalfWidths) && (Math.abs(dy) <= addedHalfHeights);
    }

    public void handleCollision(GameObject player, GameObject[] backgrounds, GameObject[] grounds, int backgroundNr, double dt) {
        // jeśli gracz nie może przeniknąć przez ten Box
        if (!this.transparent) {
            Box playerBox = player.getComponent(Box.class);

            // obliczenie aktualnych środków obiektów
            this.calculateCenter();
            playerBox.calculateCenter();

            float dx, dy;   // odległości między środkami b1 i b2 na osiach x i y
            dx = this.center.x - playerBox.center.x;
            dy = this.center.y - playerBox.center.y;

            float addedHalfWidths, addedHalfHeights;    // dodane do siebie połowy szerokości i długości b1 i b2
            addedHalfWidths = playerBox.halfWidth + this.halfWidth;
            addedHalfHeights = playerBox.halfHeight + this.halfHeight;

            float overlapX, overlapY;
            overlapX = addedHalfWidths - Math.abs(dx);
            overlapY = addedHalfHeights - Math.abs(dy);

            // jeśli kolizja pionowa
            if (overlapX >= overlapY) {
                if (dy > 0) {
                    // kolizja od dołu gracza ( wylądowanie na platformie )
                    player.getComponent(Physics.class).velocity.y = 0;
                    player.getComponent(Player.class).canJump = true;
                }
                else {
                    // kolizja od góry gracza (zderzenie z platformą od dołu)
                    player.location.position.y = player.location.position.y + overlapY + 1 ;
                    player.getComponent(Physics.class).velocity.y = 0;
                }
            }
            else {      // kolizja pozioma
                player.location.position.x = player.location.position.x - overlapX;

                // wstrzymaj tło na chwilę kolizji poziomej
                for (int i = 0; i < backgroundNr; i++) {
                    backgrounds[i].location.position.x = backgrounds[i].location.position.x -
                            (float)(backgrounds[i].getComponent(MovingBackground.class).getSpeed()*dt);
                    grounds[i].location.position.x = grounds[i].location.position.x -
                            (float)(grounds[i].getComponent(MovingBackground.class).getSpeed()*dt);
                }
            }


        }
        else {
            // kolizja z przedmiotem funkcyjnym (zwiększa wynik)
            this.collided = this.goodProp;
        }
    }
}
