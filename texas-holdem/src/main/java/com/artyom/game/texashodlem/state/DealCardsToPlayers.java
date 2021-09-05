package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.texashodlem.TexasHoldem;

import java.util.List;

public class DealCardsToPlayers implements GameState {


    @Override
    public void next(GameManager game) {
        game.setState(new BettingRound(this));
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        if(game instanceof TexasHoldem texasHoldem)
            texasHoldem.getPlayers().forEach(player -> {
            player.giveCards(List.of(texasHoldem.getDeck().takeTop(), texasHoldem.getDeck().takeTop()));
        });
    }

}
