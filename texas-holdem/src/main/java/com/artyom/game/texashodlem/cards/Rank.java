package com.artyom.game.texashodlem.cards;

public enum Rank {
    TWO(  0),
    THREE(1),
    FOUR( 2),
    FIVE( 3),
    SIX(  4),
    SEVEN(5),
    EIGHT(6),
    NINE( 7),
    TEN(  8),
    JACK( 9),
    QUEEN(10),
    KING( 11),
    ACE(  12);
    //JOKER(14);
    private final int id;
    Rank(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

}
