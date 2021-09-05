package com.artyom.game.texashodlem.players;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Player;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.cards.Card;
import com.artyom.game.texashodlem.cards.Combo;
import com.artyom.game.texashodlem.exceptions.NotEnoughChipsException;

import java.util.*;

public abstract class TexasHoldemPlayer extends Player {
    private long currentBet;
    private long chips;
    private boolean active;
    private List<Card> cards;

    TexasHoldemPlayer left;
    TexasHoldemPlayer right;

    private List<Combo> combos = new ArrayList<>();
    private int maxRank;

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

    public void countCombos(){
        List<Card> cards = new ArrayList<>();
        Collections.copy(this.cards, cards);
        cards.addAll(((TexasHoldem)this.getGame()).getTable());
        for (Combo combo : Combo.values()) {
            if(combo.countCombination(cards)){
                this.combos.add(combo);
            }
        }
    }

    public int getMaxRank() {
        return maxRank;
    }

    public void setMaxRank(int maxRank) {
        this.maxRank = maxRank;
    }

    public List<Combo> getCombos() {
        return combos;
    }
}
