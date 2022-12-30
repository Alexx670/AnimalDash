package com.components;

import com.engine.Component;
import com.utillity.Constants;
import com.utillity.Vector2;

import java.util.Map;

public class Physics extends Component {
    public Vector2 velocity;

    public Physics (Vector2 velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update(double dt) {
        gameObject.location.position.x += velocity.x * dt;
        gameObject.location.position.y += velocity.y * dt;

        velocity.y += Constants.GRAVITY * dt;   // przyspieszenie grawitacyjne

        // Prędkość obiektu nie może przekroczyć ustalonej maksymalnej wartości
        if (Math.abs(velocity.y) > Constants.MAX_VELOCITY) {
            velocity.y = Math.signum(velocity.y) * Constants.MAX_VELOCITY;
        }
    }
}
