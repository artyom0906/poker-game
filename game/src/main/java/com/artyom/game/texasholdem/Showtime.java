package com.artyom.game.texasholdem;

import com.artyom.game.Game;
import com.artyom.game.GameState;

public class Showtime implements GameState {
    @Override
    public void next(Game game) {
        game.setState(new EndRound());
    }

    @Override
    public void prev(Game game) {

    }

    @Override
    public void doAction(Game game) {

    }
}
