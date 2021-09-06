package com.artyom.game.texashodlem.cards;

import org.junit.jupiter.api.Test;


class ComboTest {
    @Test
    void highCard() {
        List<Card> cards= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.ACE, Combo.HIGH_CARD.countCombination(cards));
        List<Card> cards1= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.KING, Combo.HIGH_CARD.countCombination(cards1));
        List<Card> cards2= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.JACK, Combo.HIGH_CARD.countCombination(cards2));
    }

    @Test
    void pair() {
        List<Card> cards= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.TWO, Combo.PAIR.countCombination(cards));
    }

    @Test
    void twoPair() {
        List<Card> cards= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.KING, Suit.SPADES)
        );
        Assertions.assertEquals(Math.pow(Rank.KING.getId(), Rank.TWO.getId()+1), Combo.PAIR.countCombination(cards));
    }

    @Test
    void three() {
        List<Card> cards= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.TWO, Combo.PAIR.countCombination(cards));
    }

    @Test
    void straight() {
        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(null, Combo.STRAIGHT.countCombination(cards));

        //Royal
        List<Card> cards2 = List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.DIAMONDS),
                new Card(Rank.FOUR, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.HEARTS),
                new Card(Rank.NINE, Suit.HEARTS)
        );
        assertRank(Rank.NINE, Combo.STRAIGHT.countCombination(cards2));

        List<Card> cards3 = List.of(
                new Card(Rank.SEVEN, Suit.DIAMONDS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.NINE, Suit.HEARTS),
                new Card(Rank.TEN, Suit.CLUBS),
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS)
        );
        assertRank(Rank.ACE, Combo.STRAIGHT.countCombination(cards3));


        List<Card> cards4 = List.of(
                new Card(Rank.ACE, Suit.SPADES),
                new Card(Rank.TWO, Suit.DIAMONDS),
                //new Card(Rank.THREE, Suit.DIAMONDS),
                new Card(Rank.FOUR, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.HEARTS),
                new Card(Rank.ACE, Suit.CLUBS)
        );
        assertRank(Rank.EIGHT, Combo.STRAIGHT.countCombination(cards4));
    }


    @Test
    void flush() {
        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.ACE, Combo.FLUSH.countCombination(cards));

        List<Card> cards1 = List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.KING, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(null, Combo.FLUSH.countCombination(cards1));

        List<Card> cards2 = List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.TWO, Suit.SPADES),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.EIGHT, Suit.DIAMONDS),
                new Card(Rank.SIX, Suit.DIAMONDS)
        );
        assertRank(null, Combo.FLUSH.countCombination(cards2));

        List<Card> cards4 = List.of(
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.THREE, Suit.HEARTS),
                new Card(Rank.FOUR, Suit.HEARTS),
                new Card(Rank.FIVE, Suit.HEARTS),
                new Card(Rank.SIX, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.SIX, Combo.FLUSH.countCombination(cards4));
    }

    @Test
    void four() {
        List<Card> cards= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(Rank.TWO, Combo.FOUR.countCombination(cards));
        List<Card> cards1= List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.ACE, Suit.HEARTS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.SIX, Suit.SPADES)
        );
        assertRank(null, Combo.FOUR.countCombination(cards1));
    }

    @Test
    void straightFlush() {
        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.DIAMONDS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.DIAMONDS),
                new Card(Rank.SIX, Suit.DIAMONDS),
                new Card(Rank.SEVEN, Suit.DIAMONDS),
                new Card(Rank.EIGHT, Suit.DIAMONDS)
        );
        assertRank(Rank.EIGHT, Combo.STRAIGHT_FLUSH.countCombination(cards));
        List<Card> cards2 = List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.DIAMONDS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.DIAMONDS),
                new Card(Rank.SIX, Suit.DIAMONDS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.NINE, Suit.DIAMONDS)
        );
        assertRank(Rank.SIX, Combo.STRAIGHT_FLUSH.countCombination(cards2));
        /*
         * 1 - 2
         * 1 - 3
         * 1 - 4
         * 1 - 5
         * 1 - 6
         * 1 - ace//
         * 2 - 7
         */
        List<Card> cards3 = new ArrayList<>(List.of(
                new Card(Rank.TWO, Suit.DIAMONDS),
                new Card(Rank.THREE, Suit.DIAMONDS),
                new Card(Rank.FOUR, Suit.DIAMONDS),
                new Card(Rank.FIVE, Suit.DIAMONDS),
                new Card(Rank.SIX, Suit.DIAMONDS),
                new Card(Rank.ACE, Suit.DIAMONDS),
                new Card(Rank.SEVEN, Suit.SPADES)
        ));
        assertRank(Rank.SIX, Combo.STRAIGHT_FLUSH.countCombination(cards3));
        Collections.shuffle(cards3);
        assertRank(Rank.SIX, Combo.STRAIGHT_FLUSH.countCombination(cards3));
    }

    private static void assertRank(Rank rank, int id){
        Assertions.assertEquals(rank, Rank.toEnum(id));
    }
}