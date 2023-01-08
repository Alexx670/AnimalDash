package com.engine;

import com.utillity.Vector2;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MListener extends MouseAdapter {

    private Vector2 mousePosition;  // aktualna pozycja myszy
    //private Vector2 deltaMousePosition;
    private boolean mouseButton;    // czy naciskany jest  lewy przycisk myszy

    /*
    MListener() {
        mousePosition = new Vector2(0.0f, 0.0f);
        mouseButton = false;
    }
     */


    public Vector2 getMousePosition() {
        return new Vector2(mousePosition.x, mousePosition.y);
    }

    public boolean getMouseButton() {
        return this.mouseButton;
    }

    public void setMouseButton(boolean pressed) {
        this.mouseButton = pressed;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //this.deltaMousePosition = new Vector2((e.getX() - mousePosition.x), (e.getY() - mousePosition.y));
        this.mousePosition = new Vector2(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            this.mouseButton = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            this.mouseButton = false;
        }
    }
}
