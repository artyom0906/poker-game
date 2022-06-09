package com.artyom.game.draughts.graphics.player;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;

import java.awt.*;
import java.util.Set;

public class ComputerPlayer extends DraughtsPlayer{
    public ComputerPlayer(GameManager game, CheckerColor color, Set<Checker> checkers) {
        super(game, color, checkers);
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
}
