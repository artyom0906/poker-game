package com.artyom.game.draughts.logic.checker;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Checker {
    public CheckerColor getColor() {
        return color;
    }

    protected final CheckerColor color;
    private boolean isKing;
    protected Point2D point;

    private boolean active = true;

    protected Checker(Checker checker) {
        this.color = checker.color;
        this.isKing = checker.isKing;
        this.point = checker.point;
    }

    private Checker(CheckerColor color, Point2D point) {
        this.color = color;
        this.isKing = false;
        this.point = point;
    }

    public boolean isKing() {
        return isKing;
    }

    public void makeKing() {
        isKing = true;
    }

    public static InitialPositionBuilder builder(CheckerColor color) {
        return new InitialPositionBuilder(color);
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public record InitialPositionBuilder(CheckerColor color) {
        public Set<Checker> build() {
            Set<Checker> checkers = new HashSet<>();
            int start = color == CheckerColor.BLACK?0:5;
            int end = color == CheckerColor.BLACK?3:8;
            for (int i = 0; i < 8; i++) {
                for (int j = start; j < end; j++) {
                    if (i % 2 != j % 2)
                        checkers.add(new Checker(this.color, new Point2D.Float(i, j)));
                }
            }
            return checkers;
        }
    }

    public Point2D getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return super.toString() + "[" + this.getPoint() + " " + this.getColor() + "]";
    }

    public void setKing(boolean king) {
        isKing = king;
    }
}
