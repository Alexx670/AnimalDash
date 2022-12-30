package com.engine;

import com.utillity.Vector2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    String name;
    public Camera camera;
    List<GameObject> gameObjectList;
    Rendering rendering;

    public void Scene(String name) {
        this.name = name;
        this.camera = new Camera(new Vector2(0,0));
        this.gameObjectList = new ArrayList<>();
        this.rendering = new Rendering(this.camera);
    }

    public void addGameObject(GameObject g) {
        gameObjectList.add(g);
        rendering.send(g);
    }

    public void init() { }
    public abstract void update(double dt);
    public abstract void draw(Graphics2D g2);
}
