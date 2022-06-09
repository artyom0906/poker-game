package com.artyom.game.draughts.graphics.player;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Player;
import com.artyom.game.draughts.graphics.checker.CheckerRenderer;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class DraughtsPlayer extends Player {

    private final CheckerColor color;
    protected final CheckerRenderer render;
    public DraughtsPlayer(GameManager game, CheckerColor color, Set<Checker> checkers) {
        super(game);
        this.color = color;
        render = new CheckerRenderer(checkers);
    }

    public CheckerColor getColor() {
        return color;
    }

    @Override
    public void render(Graphics2D g) {
        render.render(g);
    }
}
