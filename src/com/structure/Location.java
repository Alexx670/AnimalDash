package com.structure;

import com.utillity.Vector2;

public class Location {
    public Vector2 position;

    public Location(Vector2 position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return "Position: ("+position.x+", "+position.y+")";
    }
}
