package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;

public class BettingRound implements GameState {
    private final GameState prevState;

    public BettingRound(GameState gameState) {
        this.prevState = gameState;
    }

    @Override
    public void next(GameManager game) {
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
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {

    }
}
