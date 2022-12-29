package com.engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rendering {
    List<GameObject> gameObjectList;
    Camera camera;

    public Rendering(Camera camera) {
        this.camera = camera;
        this.gameObjectList = new ArrayList<>();
    }

    public void send(GameObject gameObject) {
        this.gameObjectList.add(gameObject);
    }

    public void render(Graphics2D g2){
        for (GameObject g : gameObjectList) {
            g.draw(g2);
        }
    }
}
