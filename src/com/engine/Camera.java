package com.engine;

import com.utillity.Vector2;

/**
 * Klasa odpowiadająca za przechowywanie pozycji kamery w grze
 */
public class Camera {
    public Vector2 position;

    /**
     * Konstruktor parametryczny przypisujący kamerze zadaną pozycję
     * @param position pozycja kamery
     */
    public Camera(Vector2 position) {
        this.position = position;
    }

    /**
     * Metoda kopiująca daną kamerę
     * @return nowa instancja klasy Camera o parametrach takich jak instancja, z której wywołano funkcję
     */
    public Camera copy() {
        return (new Camera(new Vector2(this.position.x, this.position.y)));
    }
}
