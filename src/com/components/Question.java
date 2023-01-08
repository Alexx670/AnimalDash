package com.components;

import com.engine.Component;
import com.engine.LevelScene;
import com.engine.Window;
import com.structure.Assets;
import com.utillity.Constants;

import java.awt.*;

/**
 * Klasa odpowiedzialna za wyświetlanie pytania dla gracza.
 */
public class Question extends Component {
    Image question;
    Image cloud;

    public Question (String cloud, String question) {
        this.question = Assets.getImage(question).copy();
        this.cloud = Assets.getImage(cloud).copy();
    }

    public void setQuestion(Image question) {
        this.question = question.copy();
    }

    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        gameObject.location.position.x = scene.camera.position.x + Constants.QUESTION_OFFSET_X;
        gameObject.location.position.y = scene.camera.position.y + Constants.QUESTION_OFFSET_Y;
    }

    public void draw(Graphics2D g2) {
        // narusyj chmurkę z pytaniem
        g2.drawImage(this.cloud.image, (int) this.gameObject.location.position.x, (int) this.gameObject.location.position.y,
                (int)(this.cloud.width*this.gameObject.location.scale.x), (int)(this.cloud.height*this.gameObject.location.scale.y),null);
        // narysuj treść pytania
        g2.drawImage(this.question.image, (int) this.gameObject.location.position.x, (int) this.gameObject.location.position.y, null);
    }
}
