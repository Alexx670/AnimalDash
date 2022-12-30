package com.engine;

import com.structure.Location;
import com.utillity.Vector2;

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
            Location oldLocation = new Location(g.location.position, g.location.scale);
            g.location.position = new Vector2(g.location.position.x - camera.position.x, g.location.position.y - camera.position.y);
            g.draw(g2);
            g.location = oldLocation;
        }
    }
}
