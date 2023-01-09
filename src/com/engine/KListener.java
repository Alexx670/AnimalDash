package com.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Klasa odpowiedzialna za nasłuchiwanie naciśnięć klawiszy klawiatury.
 */
public class KListener extends KeyAdapter implements KeyListener {

    private boolean keyPressed[] = new boolean[128];    // tablica przechowująca stan naciśnięcia klawiszy

    /**
     * Metoda klasy KeyAdapter - jeśli naciśnięto klawisz, to jest to odnotowywane w tablicy keyPressed[]
     * @param e zdarzenie KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keyPressed[e.getKeyCode()] = true;
    }

    /**
     * Metoda klasy KeyAdapter - jeśli puszczono klawisz, to jest to odnotowywane w tablicy keyPressed[]
     * @param e zdarzenie KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keyPressed[e.getKeyCode()] = false;
    }

    /**
     * Metoda służaca do sprawdzenia, czy dany klawisz został wciśnięty
     * @param keyCode kod szukanego klawisza
     * @return wartość typu boolean świadcząca o stanie klawisza
     */
    public boolean isKeyPressed(int keyCode){
        return keyPressed[keyCode];
    }
}
