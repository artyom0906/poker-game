package com.artyom.game.texashodlem.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.api.cards.Card;
import com.artyom.game.texashodlem.TexasHoldem;

import java.util.ArrayList;
import java.util.List;

public class DealCardsToTable implements GameState {

    @Override
    public void next(GameManager game) {
        game.setState(new BettingRound(this));
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        if(game instanceof TexasHoldem texasHoldem) {
            List<Card> table = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Card card = texasHoldem.getDeck().takeTop();
                if (i < 3)
                    card.show();

                card.setX((int) ((-(Card.SPRITE_SCALE_X*2.5)-25) + (Card.SPRITE_SCALE_X*i+i*10)));

                table.add(card);
            }
            texasHoldem.setTable(table);
        }
    }
}
