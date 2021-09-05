package com.artyom.game.texashodlem.cards;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public void shuffle(){
        cards.clear();

        for(Rank rank: Rank.values()){
            for(Suit suit: Suit.values()){
                cards.add(new Card(rank, suit));
            }
        }

        Collections.shuffle(cards);
    }

    public synchronized Card takeTop(){
        return cards.pop();
    }
}
