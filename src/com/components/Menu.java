package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.Graphics2D;

/**
 * Klasa służąca do wyświetlania aktualnego wyniku gry.
 */
public class Menu extends Component {
    private int currentLevel;
    Image text;

    public Menu (String text) {
        this.currentLevel = 0;
        this.text         = Assets.getImage(text).copy();
    }

    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.LEVEL_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.LEVEL_OFFSET_Y;
    }

    public void draw(Graphics2D g2) {
        // narysuj napis "Menu"
        g2.drawImage(this.text.image,  (int)this.gameObject.location.position.x, (int)this.gameObject.location.position.y,
                Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);

    }

}
