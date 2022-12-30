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

import java.awt.Graphics2D;
import java.awt.Color;

public class LevelScene extends Scene{
    public GameObject player;

    String playerImage = "assets/krowcia3.png";
    String groundImage = "assets/ground3.png";

    public LevelScene(String name){
        super.Scene(name);
    }

    @Override
    public void init() {
        initAssets();

        // gracz
        player = new GameObject("Player", new Location(new Vector2(100,200), new Vector2(1.0f, 1.0f)));
        //Player playerComp = new Player(new Image(playerImage));
        Player playerComp = new Player(Assets.getImage(playerImage).copy());
        //Player playerComp = new Player(new Image(playerImage, Assets.getImage(playerImage).image));
        player.addComponent(playerComp);
        player.addComponent(new Physics(new Vector2(Constants.X_VELOCITY, 0)));  // 395
        player.addComponent(new Box(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        gameObjectList.add(player);

        // podłoże
        GameObject ground;
        ground = new GameObject("Ground", new Location(new Vector2(0, Constants.GROUND_Y), new Vector2(1.0f, 0.85f)));
        ground.addComponent(new Ground());
        //ground.addComponent(new Image(groundImage));
        ground.addComponent(Assets.getImage(groundImage).copy());
        gameObjectList.add(ground);

        // testowa platforma
        GameObject platform;
        platform = new GameObject("Platform", new Location(new Vector2(1000, 300), new Vector2(0.25f, 0.25f)));
        //platform.location.scale = 0.1f;
        //platform.addComponent(new Image(groundImage));
        platform.addComponent(Assets.getImage(groundImage).copy());
        gameObjectList.add(platform);

        addGameObject(player);
        addGameObject(ground);
        addGameObject(platform);
    }

    public void initAssets(){
        Assets.addImage(playerImage, new Image(playerImage));
        Assets.addImage(groundImage, new Image(groundImage));
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
