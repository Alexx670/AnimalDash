package com.engine;

import com.components.Box;
import com.components.Player;
import com.structure.Assets;
import com.structure.Location;
import com.utillity.Constants;
import com.utillity.Vector2;

import java.awt.*;

public class LevelScene extends Scene{
    GameObject testGameObject;

    public LevelScene(String name){
        super.Scene(name);
    }

    @Override
    public void init() {
        testGameObject = new GameObject("Test Game Object", new Location(new Vector2(100,100)));
        //testGameObject.addComponent(new Box("Box1"));
        //testGameObject.addComponent(Assets.getImage("assets/background.png"));
        Player playerComp = new Player(Assets.getImage("assets/krowcia3.png"));
        testGameObject.addComponent(playerComp);
        rendering.send(testGameObject);
    }

    @Override
    public void update(double dt) {
        //System.out.println(testGameObject.getComponent(Box.class).name);
        testGameObject.update(dt);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        rendering.render(g2);
    }
}
