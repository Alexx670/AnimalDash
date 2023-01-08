package com.components;

import com.engine.Component;
import com.engine.GameObject;
import com.engine.LevelScene;
import com.engine.Window;
import com.utillity.Constants;

import java.awt.Graphics2D;

/**
 * Klasa reprezentująca podłoże, po którym porusza się gracz
 * Dziedziczy po klasie Component
 */
public class Ground extends Component {

    /**
     * Klasa odświeżająca parametry podłoża
     * @param dt czas, który upłynął między kolejnymi wywołaniami metody
     */
    @Override
    public void update(double dt) {
        // pomocne pola, skracają dalsze zapisy
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        GameObject player = scene.player;

        // jeśli gracz ląduje na podłożu
        if ((player.location.position.y + player.getComponent(Box.class).getHeight()) >
                (gameObject.location.position.y + Constants.PLAYER_OVERLAP)) {
            // gracz nie spada niżej niż górna granica posłoża
            player.location.position.y = gameObject.location.position.y - player.getComponent(Box.class).getHeight() + Constants.PLAYER_OVERLAP;
            // gracz może skakać
            player.getComponent(Player.class).canJump = true;
        }
    }
}
