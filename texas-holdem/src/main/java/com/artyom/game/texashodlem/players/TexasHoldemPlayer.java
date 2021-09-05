package com.artyom.game.texashodlem.players;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Player;
import com.artyom.game.texashodlem.cards.Card;
import com.artyom.game.texashodlem.exceptions.NotEnoughChipsException;

import java.util.List;
import java.util.Map;

public abstract class TexasHoldemPlayer extends Player {
    private long currentBet;
    private long chips;
    private boolean active;
    private List<Card> cards;

    TexasHoldemPlayer left;
    TexasHoldemPlayer right;

    public TexasHoldemPlayer(GameManager game, long chips) {
        super(game);
        this.chips = chips;
        this.currentBet = 0L;
    }

    public void onEvent(PlayerEventHandler handler){

    }

    public long getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(long currentBet) {
        this.currentBet = currentBet;
    }

    public long getChips() {
        return chips;
    }

    private void setChips(long chips) {
        this.chips = chips;
    }

    public void takeChips(long chips) throws NotEnoughChipsException {
        if (getChips() - chips == 0)
            throw new NotEnoughChipsException("not enough chips");
        setChips(getChips() - chips);
    }

    public void giveChips(long chips) {
        setChips(getChips() + chips);
    }

    public boolean isActive() {
        return active;
    }
    public void activate() {
        this.active = true;
    }
    public void deactivate() {
        this.active = false;
    }

    public List<Card> getCards() {
        return cards;
    }
    public void giveCards(List<Card> takeTop) {
        this.cards = takeTop;
    }

    public TexasHoldemPlayer getLeft() {
        return left;
    }

    public void setLeft(TexasHoldemPlayer left) {
        this.left = left;
    }

    public TexasHoldemPlayer getRight() {
        return right;
    }

    public void setRight(TexasHoldemPlayer right) {
        this.right = right;
    }
}
