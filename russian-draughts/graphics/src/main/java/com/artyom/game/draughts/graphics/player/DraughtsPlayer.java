package com.artyom.game.draughts.graphics.player;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Player;
import com.artyom.game.draughts.graphics.checker.CheckerRenderable;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class DraughtsPlayer extends Player {

    private final CheckerColor color;
    protected final Set<CheckerRenderable> pieces;
    public DraughtsPlayer(GameManager game, CheckerColor color, Set<Checker> checkers) {
        super(game);
        this.color = color;
        this.pieces = checkers.stream().map(CheckerRenderable::new).collect(Collectors.toSet());
    }

    public CheckerColor getColor() {
        return color;
    }

    @Override
    public void render(Graphics2D g) {
        pieces.forEach(checkerRenderable -> checkerRenderable.render(g));
    }
}
