package com.engine;

import java.awt.Graphics2D;

// klasa abstrakcyjna, po której dziedziczyć będą własności obiektów GameObject
public abstract class Component<T> {
    public GameObject gameObject;

    // jeśli metoda nie zostanie nadpisana to będzie pusta
    public void start(){
    }

    // jeśli metoda nie zostanie nadpisana to będzie pusta
    public void update(double dt) {
    }

    // jeśli metoda nie zostanie nadpisana to będzie pusta
    public void draw(Graphics2D g2) {
    }
}
