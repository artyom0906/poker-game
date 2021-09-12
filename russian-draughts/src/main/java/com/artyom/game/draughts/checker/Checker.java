package com.artyom.game.draughts.checker;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Checker {
    private final CheckerColor color;
    private boolean isKing;
    private Point2D point;

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

    public record InitialPositionBuilder(CheckerColor color) {
        public List<Checker> build() {
            List<Checker> checkers = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 2; j++) {
                    if (i % 2 == j % 2)
                        checkers.add(new Checker(this.color, new Point2D.Double(i, j)));
                }
            }
            return checkers;
        }
    }
}
