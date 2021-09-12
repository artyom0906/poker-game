package com.artyom.game.draughts.checker;

import com.artyom.game.api.Entity;
import com.artyom.game.api.Input;

import java.awt.*;

public class Checker extends Entity {
    private final CheckerColor color;
    private int rank;
    private int file;
    private boolean isKing;

    public Checker(CheckerColor color) {
        super(0, 0);
        this.color = color;
        this.isKing = false;
    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void update(Input input) {

    }

    public boolean isKing() {
        return isKing;
    }

    public void makeKing() {
        isKing = true;
    }
}
