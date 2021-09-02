package com.artyom.game.texasholdem;

import com.artyom.game.Game;
import com.artyom.game.GameState;
import com.artyom.game.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class DealCardsToTable implements GameState {
    @Override
    public void next(Game game) {

    }

    @Override
    public void prev(Game game) {
        game.setState(new BettingRound(this));
    }

    @Override
    public void doAction(Game game) {
        List<Card> table = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Card card = game.getDeck().takeTop();
            if(i<3)
                card.show();

            card.setX((int) ((-(Card.SPRITE_SCALE_X*2.5)-25) + (Card.SPRITE_SCALE_X*i+i*10)));

            table.add(card);
        }
        game.setTable(table);
    }
}
