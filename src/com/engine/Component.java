package com.engine;

import java.awt.Graphics2D;

/**
 * Klasa abstrakcyjna, po której dziedziczyć będą własności obiektów GameObject.
 * Może być dołączony do dowolnego obiektu gry.
 * @param <T> parametr potrzebny do umożliwienia wyszukania po nazwie klasy dziedziczącej po tej klasie
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
