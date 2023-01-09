package com.engine;

import com.utillity.Vector2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa abstrakcyjna, po której dziedziczą wszystkie sceny w grze.
 */
public abstract class Scene {
    String name;    // nazwa sceny
    public Camera camera;   // kamera sceny
    List<GameObject> gameObjectList;    // lista obiektów gry
    Rendering rendering;    // renderowanie

    /**
     * Konstruktor parametryczny klasy Scene
     * @param name nazwa sceny
     */
    public void Scene(String name) {
        this.name = name;   // ustawienie nazwy
        this.camera = new Camera(new Vector2(0,0)); // utworzenie nowej kamery
        this.gameObjectList = new ArrayList<>();    // inicjalizacja listy obiektów gry
        this.rendering = new Rendering(this.camera);    // inicjalizacja renderingu
    }

    /**
     * Metoda służąca do dodawania obiektu gry
     * @param g dodawany obiekt gry (GameObject)
     */
    public void addGameObject(GameObject g) {
        gameObjectList.add(g);  // dodaj obiekt gry do listy
        rendering.send(g);  // wyślij obiekt do renderowania
    }

    // pusta metoda, która może zostać nadpisana w klasach dziedziczących, inicjalizuje scenę
    public void init() { }

    /**
     * Metoda odpowiadająca za aktualizację stanu sceny.
     * Metoda abstrakcyjna.
     * @param dt czas między wywołaniami metody
     */
    public abstract void update(double dt);
    /**
     * Metoda odpowiadająca za rysowanie sceny.
     * Metoda abstrakcyjna.
     * @param g2 grafika okna
     */
    public abstract void draw(Graphics2D g2);
}
