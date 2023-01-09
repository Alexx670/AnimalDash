package com.engine;

import com.structure.Location;
import com.utillity.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za renderowanie (wywoływanie rysowania) obiektów gry w oparciu o pozycję kamery.
 */
public class Rendering {
    List<GameObject> gameObjectList;    // lista obiektów gry do renderowania
    Camera camera;      // kamera śledząca gracza

    /**
     * Konstruktor parametryczny klasy Rendering
     * @param camera przekazywana kamera
     */
    public Rendering(Camera camera) {
        this.camera = camera;
        this.gameObjectList = new ArrayList<>();
    }

    /**
     * Metoda służąca do wysłania obiektu gry do renderowania.
     * @param gameObject wysyłany obiekt gry
     */
    public void send(GameObject gameObject) {
        this.gameObjectList.add(gameObject);
    }

    /**
     * Metoda służąca do renderowania obiektów gry.
     * @param g2 grafika okna gry
     */
    public void render(Graphics2D g2){
        // rysowanie obiektów gry i ewentualna zmiana ich pozycji
        for (GameObject g : gameObjectList) {
            if (g.isUI) {
                // jeśli dany game object jest ui - render nie będzie zmieniał jego lokalizacji
                g.draw(g2);
            }
            else {
                // wszystkie inne game objecty będą renderowane
                Location oldLocation = new Location(g.location.position, g.location.scale);
                g.location.position = new Vector2(g.location.position.x - camera.position.x, g.location.position.y - camera.position.y);
                g.draw(g2);
                g.location = oldLocation;
            }
        }
    }
}
