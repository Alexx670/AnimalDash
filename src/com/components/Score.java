package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Scene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

public class Score extends Component {
    int score;
    Image text;
    Image[] scoresTable = new Image[(Constants.PROP_COUNT/2)+1];

    public Score(String text, String[] scores) {
        this.score = 0;
        this.text = Assets.getImage(text).copy();

        for (int i = 0; i< Constants.PROP_COUNT/2+1; i++) {
            this.scoresTable[i] = Assets.getImage(scores[i]).copy();
        }
    }

    public void setScore(int score) { this.score = score; }

    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.SCORE_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.SCORE_OFFSET_Y;
    }

    public void draw(Graphics2D g2) {
        // narysuj napis Score
        g2.drawImage(this.text.image,  (int)this.gameObject.location.position.x, (int)this.gameObject.location.position.y,
                Constants.TEXT_WIDTH, Constants.TEXT_HEIGHT, null);
        // narysuj cyfrę odpowiadającą wynikowi
        Image nrDrawn = scoresTable[score];
        g2.drawImage(nrDrawn.image,  (int)this.gameObject.location.position.x + Constants.TEXT_WIDTH - 20,
                (int)this.gameObject.location.position.y, Constants.NUMBER_WIDTH, Constants.NUMBER_HEIGHT, null);
    }

}
