package com.artyom.game.engine.api;

import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.Renderer;

import java.awt.*;

public interface Screen {
    void update(Input input);

    void render(Graphics2D graphics);

    void init(Renderer game);
}
