package com.artyom.game.draughts.logic.checker;

public enum CheckerColor {
    BLACK {
        @Override
        public CheckerColor next() {
            return WHITE;
        }
    },
    WHITE {
        @Override
        public CheckerColor next() {
            return BLACK;
        }
    };

    public abstract CheckerColor next();
}
