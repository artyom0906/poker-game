package com.artyom.game.texashodlem.cards;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Combo {


    STRAIGHT_FLUSH( 8){
        @Override
        public int countCombination(List<Card> cards) {
            /*
             * 1 - 1
             * 1 - 2
             * 1 - 3
             * 1 - 4
             * 1 - 5
             * 1 - 9//
             * 2 - 6
             */
            List<Card> sortedCards = new ArrayList<>(cards);
            //Remove non-flush suited cards
            var flushSuit = genericCounter(Suit.class, cards)
                    .filter(e->e.getValue()>=5)
                    .map(Map.Entry::getKey)
                    .findAny();
            if(flushSuit.isPresent())
                sortedCards = sortedCards.stream()
                        .map(card -> {
                            if (card.getSuit().equals(flushSuit.get()))
                                return card;
                            else
                                return null;
                        }).filter(Objects::nonNull).collect(Collectors.toList());
            else return -1;
            //sortedCards.forEach(card -> {
            //    System.out.println("Rank: " + card.getRank() + " Suit: " + card.getSuit());
            //});
            int flushTop = Combo.FLUSH.countCombination(sortedCards);
            int straightTop = Combo.STRAIGHT.countCombination(sortedCards);
            //System.out.println("flushTop: " + flushTop + " straightTop: " + straightTop);

            if (flushTop >= straightTop)
                return straightTop;
            return -1;
        }
    },
    FOUR(           7) {
        @Override
        public int countCombination(List<Card> cards) {
            return highestRankCount(cards, 4);
        }
    },
    FULL_HOUSE(     6) {
        @Override
        public int countCombination(List<Card> cards) {

            int threeRank = highestRankCount(cards, 3);
            if (threeRank != -1) {
                int twoRank = highestRankCount(cards.stream().filter(e -> e.getRank().getId() != threeRank).collect(Collectors.toList()), 2);
                if (twoRank != -1) {
                    return (int) Math.pow(threeRank+1, twoRank+1);
                }
            }
            return -1;
        }
    },
    FLUSH(          5) {
        @Override
        public int countCombination(List<Card> cards) {
            try {
                return Objects.requireNonNull(Combo.highestFlushCard(cards)).getRank().getId();
            } catch(NullPointerException ignored) {
                return -1;
            }

        }
    },
    STRAIGHT(       4) {
        @Override
        public int countCombination(List<Card> cards) {
            List<Rank> ranks_from_cards = cards.stream().map(Card::getRank).distinct().sorted().collect(Collectors.toList());
            AtomicInteger consecutive = new AtomicInteger();
            AtomicInteger prevId = new AtomicInteger(ranks_from_cards.get(0).getId());
            ranks_from_cards.forEach(rank->{
                if(prevId.get()+1==rank.getId() || prevId.get()==rank.getId()) {
                    consecutive.getAndIncrement();
                    prevId.set(rank.getId());
                }else if(consecutive.get()<5) {
                    consecutive.set(1);
                    prevId.set(rank.getId());
                }
            });
            if(consecutive.get()>=5)
                return prevId.get();
            return -1;
        }
    },
    THREE(          3) {
        @Override
        public int countCombination(List<Card> cards) {
            return highestRankCount(cards, 3);
        }
    },
    TWO_PAIRS(      2) {
        @Override
        public int countCombination(List<Card> cards) {
            //return genericCounter(Rank.class, cards).filter(e -> e.getValue() == 2).count() >= 2;
            int highPair = highestRankCount(cards, 2);
            if(highPair!=-1) {
                int lowPair = highestRankCount(cards.stream().filter(e -> e.getRank().getId() != highPair).collect(Collectors.toList()), 2);
                if(lowPair!=-1)
                    return (int) Math.pow(highPair, lowPair + 1);
            }
            return -1;
        }
    },
    PAIR(           1) {
        @Override
        public int countCombination(List<Card> cards) {
            //return genericCounter(Rank.class, cards).anyMatch(e -> e.getValue() == 2);
            return highestRankCount(cards, 2);
        }
    },
    HIGH_CARD(      0) {
        @Override
        public int countCombination(List<Card> cards) {
            return highestRankCount(cards, 1);
        }
    };
    private final int id;
    Combo(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public abstract int countCombination(List<Card> cards);

    private static <T extends Enum<T>> Stream<Map.Entry<T, Long>> genericCounter(Class<T> enumType, List<Card> cards) {
        Map<T, Long> count= new HashMap<>();
        for(T t : enumType.getEnumConstants()) {
            count.put(t, cards.stream().filter(card -> {
                try {
                    return card.getGetParam(enumType).equals(t);
                } catch (Exception ignored) {
                    return false;
                }
            }).count());
        }
        return count.entrySet().stream();
    }
}
