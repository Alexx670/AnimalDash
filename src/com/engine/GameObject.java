package com.engine;

import com.structure.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca obiekty gry (np. gracz) oraz pamiętająca ich cechy (Componenty)
 */
public class GameObject {
    private List<Component> components; // lista komponentów (cech) obiektu gry
    private String name;    // nazwa obiektu gry
    public Location location;   // położenie i skala obiektu gry
    public boolean isUI;    // czy obiekt gry to UI

    /**
     * Konstruktor parametryczny klasy GameObject
     * @param name nazwa obiektu gry
     * @param location lokalizacja (położenie i skala) obiektu gry
     */
    public GameObject(String name, Location location) {
        this.name = name;
        this.location = location;
        this.components = new ArrayList<>();
    }

    /**
     * Funkcja zwracająca pierwszy komponent danej klasy, który jest w danym obiekcie GameObject.
     * @param componentClass klasa szukanego komponentu obiektu gry
     * @param <T> umożliwia wyszukiwanie
     * @return uchwyt do szukanego komponentu (jeśli istnieje), lub null jeśli nie znaleziono takiego komponentu
     */
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

    /**
     * Metoda pozwalająca dodać komponent do obiektu gry
     * @param c dodawany komponent
     */
    public void addComponent(Component c) {
        components.add(c);  // dodaj komponent c do listy
        c.gameObject = this;    // ustaw, że ten komponent jest przypisany do tego obiektu gry
        c.start();  // inicjalizuj komponent
    }

    /**
     * Metoda służąca do aktualizowania obiektu gry.
     * Aktualizowanie GameObject sprowadza się do aktualizowania wszystkich jego komponentów - to one decysuj o cechach obiektu gry
     */
    public void update(double dt) {
        for (Component c:components) {
            c.update(dt);
        }
    }

    /**
     * Metoda służąca do rysowania obiektu gry.
     * Rysowanie GameObject sprowadza się do rysowania wszystkich jego komponentów.
     */
    public void draw(Graphics2D g2) {
        for (Component c:components) {
            c.draw(g2);
        }
    }

    /**
     * Metoda ustawiająca, że dany obiekt gry to UI
     * @param isUI wartość logiczna przypiswana polu isUI
     */
    public void setUI(boolean isUI) {
        this.isUI = isUI;
    }

    /**
     * Metoda służąca do dostępu do nazwy obiektu gry.
     * @return nazwa obiektu gry
     */
    public String getName() {
        return name;
    }
}
