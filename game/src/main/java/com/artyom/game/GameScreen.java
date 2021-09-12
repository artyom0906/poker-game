package com.artyom.game;

import com.artyom.game.api.GameComponents;
import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.Renderer;
import com.artyom.game.engine.api.Screen;

import java.awt.*;

public class GameScreen implements Screen {

    private final GameComponents components;
    private final GameInputRegistry registry;

    public GameScreen(GameComponents components, GameInputRegistry registry) {
        this.components = components;
        this.registry = registry;
    }

    @Override
    public void init(Renderer game) {
        components.screen().init(registry);
    }

    @Override
    public void update(Input input)
    {
        components.screen().update(new com.artyom.game.api.Input(/*input*/));
    }

    @Override
    public void render(Graphics2D graphics) {
        components.screen().render(graphics);
    }

}