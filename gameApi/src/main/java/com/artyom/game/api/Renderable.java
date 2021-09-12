package com.artyom.game.api;

import java.awt.*;

public interface Renderable {
    void update(Input input);

    void render(Graphics2D g);

    default void init(GameInputRegistry registry){}
}
