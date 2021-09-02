package com.artyom.game;

public interface GameState {
    void next(Game game);
    void prev(Game game);
    void doAction(Game game);
}
