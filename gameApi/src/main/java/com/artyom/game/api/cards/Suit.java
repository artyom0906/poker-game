package com.artyom.game.api.cards;

public enum Suit {
    HEARTS(0),
    DIAMONDS(1),
    CLUBS(2),
    SPADES(3);
    private final int id;
    Suit(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}
