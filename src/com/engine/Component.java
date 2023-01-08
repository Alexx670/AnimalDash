package com.engine;

import java.awt.Graphics2D;

/**
 * Klasa abstrakcyjna, po której dziedziczyć będą własności obiektów GameObject.
 * Może być dołączony do dowolnego obiektu gry.
 * @param <T> parametr służący do wyszukania klasy po nazwie
 */
public abstract class Component<T> {
    public GameObject gameObject;   // obiekt gry, do którego przypisany jest dany Component

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
