package com.artyom.game;


/*
3-10 players
Round:
    1. Dealer give every player 2 cards.
    2. Betting round:
        They bet/call/raise/fold in clockwise order.
        If the bet is raised then repeat.
        Raise is double the prev bet or higher. Max 3 raises per betting round.
        If everyone except 1 folds, the one who is left is a winner.
    3. Dealer takes 5 top cards from the deck and shows 3 of them (2 are hidden)
    4. Repeat [2]
    5. Dealer shows the 4th card
    6. Repeat [2]
    7. Dealer shows the 5th card
    8. Repeat [2]
    9. Showtime:
        The best combo wins.
    10. End round. Winner takes the bank.
 */
public enum TexasHoldem {
    DEAL_CARDS_TO_PLAYERS{
        @Override
        public TexasHoldem nextState() {
            return BETTING_ROUND;
        }
    },
    BETTING_ROUND{
        @Override
        public TexasHoldem nextState() {
            return null;
        }
    };
    public abstract TexasHoldem nextState();
}
