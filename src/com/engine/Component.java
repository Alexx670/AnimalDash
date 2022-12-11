package com.engine;

import java.awt.*;

// klasa abstrakcyjna, po której dziedziczyć będą własności obiektów GameObject
public abstract class Component<T> {
    public GameObject gameObject;

    public void update(double dt) {
    }

    public void draw(Graphics2D g2) {
    }
}
