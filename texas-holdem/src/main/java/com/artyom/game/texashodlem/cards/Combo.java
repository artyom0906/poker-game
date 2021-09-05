package com.artyom.game.texashodlem.cards;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Combo {


    STRAIGHT_FLUSH( 8){
        @Override
        public boolean countCombination(List<Card> cards) {
            AtomicBoolean isStraightFlush = new AtomicBoolean(false);
            List<Map.Entry<Suit, Long>> flush = genericCounter(Suit.class, cards)
                    .filter(e->e.getValue()>=5)
                    .collect(Collectors.toList());
            flush.forEach(e->{
                if(STRAIGHT.countCombination(cards.stream()
                        .filter(e1->e1.getSuit().equals(e.getKey()))
                        .collect(Collectors.toList()))
                ) { isStraightFlush.set(true); }
            });
            return isStraightFlush.get();
        }
    },
    FOUR(           7) {
        @Override
        public boolean countCombination(List<Card> cards) {
            return genericCounter(Rank.class, cards).anyMatch(e -> e.getValue() == 4);
        }
    },
    FULL_HOUSE(     6) {
        @Override
        public boolean countCombination(List<Card> cards) {
            List<Map.Entry<Rank, Long>> threes = genericCounter(Rank.class, cards)
                    .filter(e->e.getValue()>=3)
                    .collect(Collectors.toList());
            boolean two = Combo.genericCounter(Rank.class, cards)
                    .filter(e->!threes.contains(e))
                    .anyMatch(e -> e.getValue() == 2);
            return (two && !threes.isEmpty()) || threes.size() == 2;
        }
    },
    FLUSH(          5) {
        @Override
        public boolean countCombination(List<Card> cards) {
            return genericCounter(Suit.class, cards).anyMatch(e->e.getValue()>=5);
        }
    },
    STRAIGHT(       4) {
        @Override
        public boolean countCombination(List<Card> cards) {
            List<Rank> ranks = cards.stream().map(Card::getRank).sorted().collect(Collectors.toList());

            int consecutive = 0;
            Rank[] values = Rank.values();
            for (int i = 0; i < ranks.size(); i++) {
                if (values[i] == ranks.get(i)) {
                    consecutive++;
                } else if(consecutive == 5) {
                    return true;
                } else {
                    consecutive = 0;
                }
            }
            return false;
        }
    },
    THREE(          3) {
        @Override
        public boolean countCombination(List<Card> cards) {
            return genericCounter(Rank.class, cards).anyMatch(e -> e.getValue() == 3);
        }
    },
    TWO_PAIRS(      2) {
        @Override
        public boolean countCombination(List<Card> cards) {
            return genericCounter(Rank.class, cards).filter(e -> e.getValue() == 2).count() >= 2;
        }
    },
    PAIR(           1) {
        @Override
        public boolean countCombination(List<Card> cards) {
            return genericCounter(Rank.class, cards).anyMatch(e -> e.getValue() == 2);
        }
    },
    HIGH_CARD(      0) {
        @Override
        public boolean countCombination(List<Card> cards) {
            return true;
        }
    };
    private final int id;
    Combo(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public abstract boolean countCombination(List<Card> cards);

    private static <T extends Enum<T>> Stream<Map.Entry<T, Long>> genericCounter(Class<T> enumType, List<Card> cards) {
        Map<T, Long> count= new HashMap<>();
        for(T rank : enumType.getEnumConstants()) {
            count.put(rank, cards.stream().filter(card -> card.getRank().equals(rank)).count());
        }
        return count.entrySet().stream();
    }
}
