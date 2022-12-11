package com.structure;

import com.utillity.Vector2;

public class Position {
    public Vector2 position;

    public Position(Vector2 position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return "Position: ("+position.x+", "+position.y+")";
    }
}
