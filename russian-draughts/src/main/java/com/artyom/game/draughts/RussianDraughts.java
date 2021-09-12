package com.artyom.game.draughts;

import com.artyom.game.api.*;
import com.artyom.game.draughts.state.GameStart;
import java.awt.*;
import java.util.ArrayList;

public class RussianDraughts extends GameManager implements GameScreen {
    private Board board = new Board(0, 0);


    public RussianDraughts() {
        super(new GameStart(), new ArrayList<>());
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
