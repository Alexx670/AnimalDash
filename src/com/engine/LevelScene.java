package com.engine;

import com.components.Box;
import com.components.Ground;
import com.components.Image;
import com.components.Physics;
import com.components.Player;
import com.structure.Assets;
import com.structure.Location;
import com.utillity.Constants;
import com.utillity.Vector2;

import java.awt.*;

public class LevelScene extends Scene{
    public GameObject player;

    public LevelScene(String name){
        super.Scene(name);
    }

    @Override
    public void init() {
        // gracz
        player = new GameObject("Player", new Location(new Vector2(100,200), 1.0f));
        Player playerComp = new Player(Assets.getImage("assets/krowcia3.png"));
        player.addComponent(playerComp);
        player.addComponent(new Physics(new Vector2(250, 0)));  // 395
        player.addComponent(new Box(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        gameObjectList.add(player);

        // podłoże
        GameObject ground;
        ground = new GameObject("Ground", new Location(new Vector2(0, Constants.GROUND_Y), 1.0f));
        ground.addComponent(new Ground());
        ground.addComponent(new Image("assets/ground2.png"));
        gameObjectList.add(ground);

        // testowa platforma
        GameObject platform;
        platform = new GameObject("Platform1", new Location(new Vector2(1000, 250), 0.3f));
        //platform.location.scale = 0.1f;
        platform.addComponent(new Image("assets/ground2.png"));
        gameObjectList.add(platform);

        addGameObject(player);
        addGameObject(ground);
        addGameObject(platform);
    }

    @Override
    public void update(double dt) {

        if (player.location.position.x - camera.position.x > Constants.CAMERA_OFFSET_X) {
            camera.position.x = player.location.position.x - Constants.CAMERA_OFFSET_X;
        }
        if (player.location.position.y - camera.position.y > Constants.CAMERA_OFFSET_Y) {
            camera.position.y = player.location.position.y - Constants.CAMERA_OFFSET_Y;
        }
        if (camera.position.y > Constants.CAMERA_OFFSET_GROUND_Y) {
            camera.position.y = Constants.CAMERA_OFFSET_GROUND_Y;
        }

        for (GameObject g : gameObjectList) {
            g.update(dt);
        }

        //camera.position.x -= dt * 15.0f;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        rendering.render(g2);
    }
}
