package com.artyom.game.api;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GameInputRegistry implements MouseListener {
    List<InputHandler> handlers = new ArrayList<>();
    public void register(InputHandler handler){
        handlers.add(handler);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e);
        System.out.println(handlers);
        handlers.forEach(handler->handler.mouseClicked(e.getPoint(), e.getButton()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        handlers.forEach(handlers->handlers.mousePressed(e.getPoint(), e.getButton()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        handlers.forEach(handlers->handlers.mouseReleased(e.getPoint(), e.getButton()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
