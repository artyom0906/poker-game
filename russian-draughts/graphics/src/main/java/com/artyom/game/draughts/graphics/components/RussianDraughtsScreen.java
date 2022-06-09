package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.GameScreen;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.logic.board.Board;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.graphics.board.BoardRenderable;

import java.awt.*;

public class RussianDraughtsScreen implements GameScreen {

    private BoardRenderable BoardRenderable;
    private RussianDraughtsManager russianDraughtsManager;

    public RussianDraughtsScreen(RussianDraughtsManager russianDraughtsManager) {
        this.russianDraughtsManager = russianDraughtsManager;
        BoardRenderable = new BoardRenderable();
    }

    @Override
    public void init(GameInputRegistry registry) {
        this.russianDraughtsManager.getPlayers().forEach(player -> player.init(registry));
        BoardRenderable.init(registry);
    }

    @Override
    public void render(Graphics2D graphics) {
        BoardRenderable.render(graphics);
        this.russianDraughtsManager.getPlayers().forEach(player -> player.render(graphics));
    }

    @Override
    public void update(Input input) {
    }
}

