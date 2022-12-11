package engine;

import utillity.Constants;

import java.awt.*;

public class LevelScene extends Scene{
    public LevelScene(String name){
        super.Scene(name);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double dt) {
        System.out.println("Lvlscene");
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
    }
}
