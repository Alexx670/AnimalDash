package com.components;

import com.engine.Component;

public class Box extends Component {

    public String name;

    public Box(String name){
        this.name = name;
    }

    @Override
    public void update(double dt) {
        System.out.println("Box Class");    // Testowe wyjście
    }
}
