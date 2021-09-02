package com.artyom.game.texasholdem;

import com.artyom.game.Game;
import com.artyom.game.GameState;

import java.util.List;

public class DealCardsToPlayers implements GameState {


    @Override
    public void next(Game game) {
        game.setState(new BettingRound(this));
    }

    @Override
    public void prev(Game game) {

    }

    @Override
    public void doAction(Game game) {
        game.getPlayers().forEach(player -> {
            player.giveCards(List.of(game.getDeck().takeTop(), game.getDeck().takeTop()));
        });
    }

}
