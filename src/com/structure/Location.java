package com.structure;

import com.utillity.Vector2;

/**
 * Klasa odpowiedzialna za przechowywanie pozycji i skali obiektu gry.
 */
public class Location {
    public Vector2 position;    // pozycja obiektu (na osi x i y)
    public Vector2 scale;   // skala obiektu (na osi x i y)

    /**
     * Konstruktor parametryczny klasy Location.
     * @param position przypisywana pozycja
     * @param scale przypisywana skala
     */
    public Location(Vector2 position, Vector2 scale) {
        this.position = position;
        this.scale = scale;
    }

}
