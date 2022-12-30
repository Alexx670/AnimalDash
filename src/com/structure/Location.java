package com.structure;

import com.utillity.Vector2;

public class Location {
    public Vector2 position;
    public float scale;

    public Location(Vector2 position, float scale) {
        this.position = position;
        this.scale = scale;
    }

    @Override
    public String toString(){
        return "Position: ("+position.x+", "+position.y+")";
    }
}
