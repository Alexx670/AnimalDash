package com.structure;

import com.utillity.Vector2;

public class Location {
    public Vector2 position;
    public Vector2 scale;

    public Location(Vector2 position, Vector2 scale) {
        this.position = position;
        this.scale = scale;
    }

    @Override
    public String toString(){
        return "Position: ("+position.x+", "+position.y+")";
    }
}
