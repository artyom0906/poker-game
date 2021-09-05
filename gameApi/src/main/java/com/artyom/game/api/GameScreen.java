package com.artyom.game.api;

import java.awt.*;



public interface GameScreen {

    void init();
    void render(Graphics2D graphics);
    void update(Input input);
}
