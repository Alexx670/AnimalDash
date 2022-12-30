package com.components;

import com.engine.Component;
import com.engine.GameObject;
import com.engine.LevelScene;
import com.engine.Window;
import com.utillity.Constants;

import java.awt.Graphics2D;
import java.awt.Color;


public class Ground extends Component {

    @Override
    public void update(double dt) {
        LevelScene scene = (LevelScene)(Window.getWindow().getCurrentScene());
        GameObject player = scene.player;

        if ((player.location.position.y + player.getComponent(Box.class).height) > (gameObject.location.position.y + Constants.PLAYER_OVERLAP)) {
            player.location.position.y = gameObject.location.position.y - player.getComponent(Box.class).height + Constants.PLAYER_OVERLAP;
        }

        gameObject.location.position.x = scene.camera.position.x - 10;
    }

    @Override
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.GREEN);

        //g2.drawRect((int)gameObject.location.position.x - 10, (int)gameObject.location.position.y + 10,
                //Constants.SCREEN_WIDTH+20, Constants.SCREEN_HEIGHT+20);
    }
}
