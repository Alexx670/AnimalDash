package com.engine;

import com.utillity.Vector2;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Klasa odpowiedzialna za śledzenie ruchu myszki oraz kliknięcka jej lewego przycisku.
 * Dziedziczy po klasie MouseAdapter
 */
public class MListener extends MouseAdapter {

    private Vector2 mousePosition;  // aktualna pozycja myszy
    private boolean mouseButton;    // czy naciskany jest  lewy przycisk myszy

    /**
     * Metoda służąca do uzyskania aktualnej pozycji myszy.
     * @return aktualna pozycja myszy
     */
    public Vector2 getMousePosition() {
        return new Vector2(mousePosition.x, mousePosition.y);
    }

    /**
     * Metoda służąca do uzyskania informacji o stanie naciśnięcia lewego przycisku myszy.
     * @return stan naciśnięcia lewego przycisku myszy
     */
    public boolean getMouseButton() {
        return this.mouseButton;
    }

    /**
     * Metoda służąca do ustawienia stanu naciśnięcia lewego przycisku myszy.
     * Przydatna do unikania podwójnych kliknięć.
     * @return przypisywany stan naciśnięcia lewego przycisku myszy
     */
    public void setMouseButton(boolean pressed) {
        this.mouseButton = pressed;
    }

    /**
     * Nadpisana metoda z klasy MouseAdapter.
     * Sledzi pozycję myszy i zapisuje ją na stosownych polach.
     * @param e zdarzenie myszy
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        this.mousePosition = new Vector2(e.getX(), e.getY());
    }

    /**
     * Nadpisana metoda z klasy MouseAdapter.
     * Reaguje na niaciśnięcie lewego przycisku myszy i zapisuje jego stan.
     * @param e zdarzenie myszy
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            this.mouseButton = true;
        }
    }

    /**
     * Nadpisana metoda z klasy MouseAdapter.
     * Reaguje na puszczenie lewego przycisku myszy i zapisuje jego stan.
     * @param e zdarzenie myszy
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            this.mouseButton = false;
        }
    }
}
