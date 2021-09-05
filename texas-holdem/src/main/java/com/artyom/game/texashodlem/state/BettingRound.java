package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.api.PlayerEvent;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.exceptions.NotEnoughChipsException;
import com.artyom.game.texashodlem.players.TexasHoldemEvent;
import com.artyom.game.texashodlem.players.TexasHoldemPlayer;



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
    private void call(TexasHoldemPlayer player, TexasHoldem texasHoldem){
        if (texasHoldem.getCurrentBet() > player.getCurrentBet()) {
            try {
                long call = texasHoldem.getCurrentBet() - player.getCurrentBet();
                player.takeChips(call);
                texasHoldem.setBank(texasHoldem.getBank() + call);
            } catch (NotEnoughChipsException ignored) {}
        }
    }
}
