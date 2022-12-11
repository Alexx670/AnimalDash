package com.engine;

import com.structure.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// klasa zawierająca obiekty gry (np. gracz)
public class GameObject {
    private List<Component> components;
    private String name;
    public  Position position;

    public GameObject(String name, Position position) {
        this.name = name;
        this.position = position;
        this.components = new ArrayList<>();
    }

    // funkcja zwracająca pierwszy komponent danej klasy, który jest w danym obiekcie GameObject
    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component c:components) {
            if (componentClass.isAssignableFrom(c.getClass())) {    // jeśli c i componentClass to te same klasy (działa też dla klas abstrakcyjnych)
                // spróbuj zwrócić klasę komponentu
                try {
                    return componentClass.cast(c);
                } catch (ClassCastException e) {    // jeśli rzutowanie się nie powiodło - exception
                    e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
        // jeśli nie znaleziono komponentu
        return null;
    }

    public void addComponent(Component c) {
        components.add(c);
        c.gameObject = this;
    }

    // aktualizowanie GameObject sprowadza się do akrualizowania jego componentów
    public void update(double dt) {
        for (Component c:components) {
            c.update(dt);
        }
    }

    public void draw(Graphics2D g2) {
        for (Component c:components) {
            c.draw(g2);
        }
    }
}
