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
    // wartości dostępne publicznie jako pola klasy ze względu na ich częste wykorzystanie
    public GameObject player;
    public Box playerBox;

    String playerImage = "assets/krowcia4.png";
    String groundImage = "assets/ground3.png";

    public LevelScene(String name){
        super.Scene(name);
    }

    @Override
    public void init() {
        initAssets();

        // gracz
        player = new GameObject("Player", new Location(new Vector2(100,200), new Vector2(1.0f, 1.0f)));
        Player playerComp = new Player(Assets.getImage(playerImage).copy());
        player.addComponent(playerComp);
        player.addComponent(new Physics(new Vector2(Constants.X_VELOCITY, 0)));  // 395
        player.addComponent(new Box(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        playerBox = player.getComponent(Box.class);

        // podłoże
        GameObject ground;
        ground = new GameObject("Ground", new Location(new Vector2(0, Constants.GROUND_Y), new Vector2(1.0f, 0.9f)));
        ground.addComponent(new Ground());
        ground.addComponent(Assets.getImage(groundImage).copy());

        /*
        // testowa platforma
        GameObject platform;
        platform = new GameObject("Platform1", new Location(new Vector2(1000, 300), new Vector2(0.25f, 0.25f)));
        platform.addComponent(Assets.getImage(groundImage).copy());
        float platformWidth = platform.getComponent(Image.class).width * platform.location.scale.x;
        float platformHeight = platform.getComponent(Image.class).height * platform.location.scale.y;
        platform.addComponent(new Box(platformWidth, platformHeight));

         */

        generateLevel(1);

        rendering.send(player);
        addGameObject(ground);
        //addGameObject(platform);
    }

    public void initAssets(){
        Assets.addImage(playerImage, new Image(playerImage));
        Assets.addImage(groundImage, new Image(groundImage));
    }


    public void generateLevel (int levelNumber) {
        GameObject platform;
        float platformWidth;
        float platformHeight;
        Vector2[] levelMatrix = new Vector2[10];
        if (levelNumber == 1) {
            // generuj Poziom 1
            levelMatrix[0] = new Vector2(1000, 300);
            levelMatrix[1] = new Vector2(1500, 300);
            levelMatrix[2] = new Vector2(2000, 100);
            levelMatrix[3] = new Vector2(3000, 300);
            levelMatrix[4] = new Vector2(3000, 100);
            levelMatrix[5] = new Vector2(3500, 300);
            levelMatrix[6] = new Vector2(4000, 100);
            levelMatrix[7] = new Vector2(4500, 100);
            levelMatrix[8] = new Vector2(5000, 300);
            levelMatrix[9] = new Vector2(5500, 100);
            for (int i = 0; i<10; i++){
                platform = new GameObject(("Platform" + i), new Location(levelMatrix[i], new Vector2(0.25f, 0.25f)));
                platform.addComponent(Assets.getImage(groundImage).copy());
                platformWidth = platform.getComponent(Image.class).width * platform.location.scale.x;
                platformHeight = platform.getComponent(Image.class).height * platform.location.scale.y;
                platform.addComponent(new Box(platformWidth, platformHeight));
                addGameObject(platform);
            }
        }
        else if (levelNumber == 2) {
            // generuj Poziom 2
        }
        else {
            // błąd, nie powinno dojść do takiej sytuacji
            System.exit(-1);
        }
    }

    @Override
    public void update(double dt) {

        if (player.location.position.x - camera.position.x > Constants.CAMERA_OFFSET_X) {
            camera.position.x = player.location.position.x - Constants.CAMERA_OFFSET_X;
        }

        camera.position.y = player.location.position.y - Constants.CAMERA_OFFSET_Y;


        if (camera.position.y > Constants.CAMERA_OFFSET_GROUND_Y) {
            camera.position.y = Constants.CAMERA_OFFSET_GROUND_Y;
        }

        player.update(dt);
        player.getComponent(Player.class).canJump = false;

        for (GameObject g : gameObjectList) {
            g.update(dt);

            Box box = g.getComponent(Box.class);
            if (box != null) {
                if (Box.checkCollision(playerBox, box)) {
                    box.handleCollision(player);
                }
            }
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
