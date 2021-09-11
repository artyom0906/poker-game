package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.api.PlayerEvent;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.players.HumanPlayer;
import com.artyom.game.texashodlem.players.TexasHoldemEvent;
import com.artyom.game.texashodlem.players.TexasHoldemPlayer;

public class EndRound implements GameState {
    @Override
    public void next(GameManager game) {
        game.setState(new StartRound());
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        //TODO: Divide bank among multiple winners (requires a lot of work)

        if (game instanceof TexasHoldem texasHoldem) {
            //texasHoldem.getPlayers().forEach(player -> {
            //    if (player instanceof HumanPlayer humanPlayer) {
            //        humanPlayer.endOfRound();
            //    }
            //});
            texasHoldem.getWinner().giveChips(texasHoldem.getBank());
            texasHoldem.setBank(0L);
            texasHoldem.setCurrentBet(0L);
            texasHoldem.getPlayers().forEach(player -> {
                if (player instanceof TexasHoldemPlayer texasHoldemPlayer)
                    texasHoldemPlayer.setCurrentBet(0L);
            });
        }

        //TODO: add "next round" button
        //game.nextState();
    }

    @Override
    public void handleEvent(PlayerEvent event, GameManager game) {
        if (game instanceof TexasHoldem texasHoldem
                && event instanceof TexasHoldemEvent texasHoldemEvent) {
            switch (texasHoldemEvent.getEvent()) {
                case "newround" -> {
                    texasHoldem.nextState();
                    texasHoldem.doAction();
                }
                case "disconnect" -> {
                    System.exit(0);
                }
            }
        }
    }
}
