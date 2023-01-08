package com.components;

import com.engine.Component;
import com.utillity.Constants;
import com.utillity.Vector2;

/**
 * Klasa odpowiedzialna za realizację fizyki gry (grawitacji i  szybkości)
 * Dziedziczy po klasie Component.
 */
public class Physics extends Component {
    public Vector2 velocity;    // prędkość obiektu gry

    /**
     * Konstruktor parametryczny klasy Physics
     * @param velocity prędkość obiektu gry
     */
    public Physics (Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * Metoda odpowiedzialna za aktualizację stanu prędkości obiekutu
     * @param dt odstęp czasu między wywołaniami funkcji
     */
    @Override
    public void update(double dt) {
        // pozycja obiektu zmienia się zgodnie z jego prędkością i czasem, który upłynął między wywołaniami funkcji
        gameObject.location.position.x += velocity.x * dt;
        gameObject.location.position.y += velocity.y * dt;

        velocity.y += Constants.GRAVITY * dt;   // przyspieszenie grawitacyjne

        // Prędkość obiektu nie może przekroczyć ustalonej maksymalnej wartości
        if (Math.abs(velocity.y) > Constants.MAX_VELOCITY) {
            velocity.y = Math.signum(velocity.y) * Constants.MAX_VELOCITY;
        }
    }
}
