package com.utillity;

/**
 * Klasa odpowiedzialna za przechowywanie dwuwymiarowego wektora złożonego z liczb typu float.
 */
public class Vector2 {
    public float x, y;  // przechowywane wartości

    /**
     * Konstruktor parametryczny klasy Vector2
     * @param x wartość x
     * @param y wartość y
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Konstruktor domyślny klasy Vector2.
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

}
