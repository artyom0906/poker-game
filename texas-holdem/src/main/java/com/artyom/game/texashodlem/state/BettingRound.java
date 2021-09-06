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
        else if (prevState instanceof Show4thCard){
            game.setState(new Show5thCard());
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
        if (game instanceof TexasHoldem texasHoldem) {
            if (prevState instanceof DealCardsToPlayers) {
                long initialBet = 10L;
                texasHoldem.setCurrentBet(initialBet);
                texasHoldem.getPlayers().forEach(player -> {
                    try {
                        ((TexasHoldemPlayer)player).takeChips(initialBet);
                    } catch (NotEnoughChipsException ignored) {}
                    texasHoldem.setBank(texasHoldem.getBank() + initialBet);
                    ((TexasHoldemPlayer) player).setCurrentBet(initialBet);
                });
            }
            ((TexasHoldemPlayer)texasHoldem.getPlayers().get(0)).activate();
        }
    }

    @Override
    public void handleEvent(PlayerEvent event, GameManager game) {
        if (game instanceof TexasHoldem texasHoldem
                && event instanceof TexasHoldemEvent texasHoldemEvent
                && event.getPlayer() instanceof TexasHoldemPlayer player) {
            switch(texasHoldemEvent.getEvent()){
                case "call"-> call(player, texasHoldem);
                case "raised"->{
                    call(player, texasHoldem);
                    long bet = 10L;
                    try {
                        player.takeChips(bet);
                        texasHoldem.setBank(texasHoldem.getBank() + bet);
                        texasHoldem.setCurrentBet(texasHoldem.getCurrentBet() + bet);
                        player.setCurrentBet(texasHoldem.getCurrentBet());
                    } catch (NotEnoughChipsException ignored) {}
                }
                case "fold"->{
                    player.getLeft().setRight(player.getRight());
                    player.getRight().setLeft(player.getLeft());
                }
            }
            player.deactivate();
            try {
                player.getLeft().activate();
            } catch (NullPointerException ignored) {
                texasHoldem.nextState();
            }
        }
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
