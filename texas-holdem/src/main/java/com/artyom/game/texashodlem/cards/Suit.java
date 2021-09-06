package com.artyom.game.texashodlem.cards;

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
    public static Suit toEnum(Integer id) {
        for (Suit value : Suit.values()) {
            if (id.equals(value.getId()))
                return value;
        }
        return null;
    }
}
