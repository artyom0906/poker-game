package com.artyom.game.api;

public interface StateDependent {
    void nextState(GameState state);
}
