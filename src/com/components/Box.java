package com.components;

import com.engine.Component;

public class Box extends Component {

    public float width;
    public float height;

    public Box(float width, float height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double dt) {
        //System.out.println("Box Class");    // Testowe wyjście
    }
}
