package com.artyom.game.api;

public interface GameState {
    void next(GameManager game);
    void prev(GameManager game);
    void doAction(GameManager game);
}
