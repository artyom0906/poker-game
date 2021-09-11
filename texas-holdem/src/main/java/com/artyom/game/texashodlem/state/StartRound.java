package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.players.HumanPlayer;

import java.util.List;

public class StartRound implements GameState {

    @Override
    public void next(GameManager game) {
        game.setState(new DealCardsToPlayers());
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        if (game instanceof TexasHoldem texasHoldem) {
            texasHoldem.setTable(List.of());
            texasHoldem.getDeck().shuffle();
        }
        game.nextState();
    }
}
