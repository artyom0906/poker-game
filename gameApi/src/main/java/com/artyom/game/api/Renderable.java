package com.artyom.game.api;

import java.awt.*;

public interface Renderable {
    default void  update(Input input){};

    void render(Graphics2D g);

    default void init(GameInputRegistry registry){}
}
