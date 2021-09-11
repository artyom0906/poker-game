package com.artyom.game.texashodlem.cards;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<>();

    /**
     * Creates a new shuffled deck
     */
    public void shuffle() {
        cards.clear();
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    /**
     * @return pops top card from deck and returns it
     */
    public synchronized Card takeTop(){
        return cards.pop();
    }
}
