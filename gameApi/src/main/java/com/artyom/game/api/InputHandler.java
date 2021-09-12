package com.artyom.game.api;

import java.awt.*;

public interface InputHandler {
    default void mouseClicked(Point position, int button){}
    default void mouseReleased(Point point, int button) {}
    default void mousePressed(Point point, int button) {}
}
