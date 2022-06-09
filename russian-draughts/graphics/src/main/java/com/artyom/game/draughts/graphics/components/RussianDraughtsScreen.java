package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.GameScreen;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.graphics.board.BoardRenderer;

import java.awt.*;

public class RussianDraughtsScreen implements GameScreen {

    private BoardRenderer BoardRenderer;
    private RussianDraughtsManager russianDraughtsManager;

    public RussianDraughtsScreen(RussianDraughtsManager russianDraughtsManager) {
        this.russianDraughtsManager = russianDraughtsManager;
        BoardRenderer = new BoardRenderer();
    }

    @Override
    public void init(GameInputRegistry registry) {
        this.russianDraughtsManager.getPlayers().forEach(player -> player.init(registry));
        BoardRenderer.init(registry);
    }

    @Override
    public void render(Graphics2D graphics) {
        BoardRenderer.render(graphics);
        this.russianDraughtsManager.getPlayers().forEach(player -> player.render(graphics));
    }

    @Override
    public void update(Input input) {
    }
}

