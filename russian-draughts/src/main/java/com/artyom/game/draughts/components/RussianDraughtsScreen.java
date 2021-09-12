package com.artyom.game.draughts.components;

import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.GameScreen;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.board.BoardRenderable;

import java.awt.*;

public class RussianDraughtsScreen implements GameScreen {

    private BoardRenderable board;

    public RussianDraughtsScreen(RussianDraughtsManager russianDraughtsManager) {
        board = new BoardRenderable(russianDraughtsManager.getBoard());
    }

    @Override
    public void init(GameInputRegistry registry) {

    }

    @Override
    public void render(Graphics2D graphics) {
        board.render(graphics);
    }

    @Override
    public void update(Input input) {
        board.update(input);
    }
}

