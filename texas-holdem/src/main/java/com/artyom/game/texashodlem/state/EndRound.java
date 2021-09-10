package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;

public class EndRound implements GameState {
    @Override
    public void next(GameManager game) {
        game.setState(new DealCardsToPlayers());
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        //TODO: give bank to winner
        //TODO: add "next round" button
        //game.nextState();
    }

    //@Override
    //public void handleEvent(PlayerEvent event, GameManager game) {
    //    if (game instanceof TexasHoldem texasHoldem
    //            && event instanceof TexasHoldemEvent texasHoldemEvent) {
    //        switch (texasHoldemEvent.getEvent()) {
    //            case "newround" -> {
    //                texasHoldem.nextState();
    //                texasHoldem.doAction();
    //            }
    //            case "disconnect" -> {
    //                System.exit(0);
    //            }
    //        }
    //    }
    //}
}
