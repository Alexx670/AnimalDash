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
    private int currentLevel;
    Image text;
    Image[] numbersTable = new Image[(Constants.PROP_COUNT/2)+1];

    public Level(String text, String[] scores) {
        this.currentLevel = 0;
        this.text         = Assets.getImage(text).copy();

        for (int i = 0; i< Constants.PROP_COUNT/2+1; i++) {
            this.numbersTable[i] = Assets.getImage(scores[i]).copy();
        }
    }

    public void setCurrentLevel(int currentLevel) {this.currentLevel = currentLevel; }

    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.LEVEL_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.LEVEL_OFFSET_Y;
    }

    public void draw(Graphics2D g2) {
        // narysuj napis "Level"
        g2.drawImage(this.text.image,  (int)this.gameObject.location.position.x, (int)this.gameObject.location.position.y,
                Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
        // narysuj cyfrę odpowiadającą poziomowi
        Image nrDrawn = numbersTable[currentLevel];
        g2.drawImage(nrDrawn.image,  (int)this.gameObject.location.position.x + Constants.TEXT_WIDTH - 20,
                (int)this.gameObject.location.position.y, Constants.NUMBER_WIDTH, Constants.NUMBER_HEIGHT, null);
    }

}
