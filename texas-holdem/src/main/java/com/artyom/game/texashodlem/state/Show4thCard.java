package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.texashodlem.TexasHoldem;

import java.util.Map;

public class Show4thCard implements GameState {
    @Override
    public void next(GameManager game) {
        game.setState(new BettingRound(this));
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        ((TexasHoldem)game).showCards(4);
        game.nextState();
    }
}
