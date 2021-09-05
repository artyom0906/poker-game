package com.artyom.game.api;

import java.util.Map;

public interface GameState {
    void next(GameManager game);
    void prev(GameManager game);
    void doAction(GameManager game);
    default void handleEvent(PlayerEvent event, GameManager game){};
}
