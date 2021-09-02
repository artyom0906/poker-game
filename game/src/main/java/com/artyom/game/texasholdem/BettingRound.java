package com.artyom.game.texasholdem;

import com.artyom.game.Game;
import com.artyom.game.GameState;

public class BettingRound implements GameState {
    private final GameState prevState;

    public BettingRound(GameState gameState) {
        this.prevState = gameState;
    }

    @Override
    public void next(Game game) {
        if(prevState instanceof DealCardsToPlayers){
            game.setState(new DealCardsToTable());
        }
        else if(prevState instanceof DealCardsToTable){
            game.setState(new Show4thCard());
        }
        else if (prevState instanceof Show5thCard){
            game.setState(new Showtime());
        }
    }

    @Override
    public void prev(Game game) {

    }

    @Override
    public void doAction(Game game) {

    }
}
