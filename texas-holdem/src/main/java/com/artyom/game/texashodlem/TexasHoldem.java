package com.artyom.game.texashodlem;

import com.artyom.game.api.*;
import com.artyom.game.texashodlem.cards.Card;
import com.artyom.game.texashodlem.cards.Deck;
import com.artyom.game.texashodlem.players.TexasHoldemPlayer;
import com.artyom.game.texashodlem.state.DealCardsToPlayers;
import com.artyom.game.texashodlem.state.StartRound;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TexasHoldem extends GameManager implements GameScreen {

    public static final int		WIDTH			= 800;
    public static final int		HEIGHT			= 600;

    private volatile GameState state = new DealCardsToPlayers();
    private final Deck deck;

    private final int DEFAULT_CHIPS = 1000;
    private long bank;
    private long currentBet;
    private AtomicBoolean nextState = new AtomicBoolean(true);

    private TexasHoldemPlayer winner;

    private List<Card> table;
    private List<TexasHoldemPlayer> currentPlayers;

    public TexasHoldem(){
        super(new StartRound(), new ArrayList<>());
        this.table = new ArrayList<>();
        deck = new Deck();
        deck.shuffle();
        this.currentPlayers = new ArrayList<>();
    }

    public GameState getState(){ return state;}

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public synchronized void doAction() {state.doAction(this);}

    public void setState(GameState state) {
        this.state = state;
        nextState.set(true);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setTable(List<Card> table){
        this.table = table;
    }

    public long getBank() {
        return bank;
    }

    public void setBank(long bank) {
        this.bank = bank;
    }

    public void showCards(int num){
        table.stream().limit(num).forEach(Card::show);
    }

    @Override
    public void init() {
        getPlayers().forEach(player -> {
            ((TexasHoldemPlayer)player).onEvent(events -> {
                state.handleEvent(events, this);
            });
        });
    }

    @Override
    public void update(Input input) {
        if(nextState.get()){
                nextState.set(false);
                this.doAction();
        }
        this.getPlayers().forEach(player -> player.update(input));
    }

    @Override
    public void render(Graphics2D graphics)
    {
        if(!table.isEmpty()) {
            AffineTransform transform = new AffineTransform();
            transform.translate(WIDTH / 2d, HEIGHT / 2d);
            transform.translate(0, -Card.SPRITE_SCALE_Y / 2d);
            graphics.setTransform(transform);
            this.table.forEach(card -> {
                card.render(graphics);
            });
            graphics.setTransform(new AffineTransform());
        }
        this.getPlayers().forEach(player -> player.render(graphics));
        graphics.drawString("Bank: " + this.getBank(), WIDTH / 2 + 40, HEIGHT / 4 + 15);
        graphics.drawString("Current bet: " + this.getCurrentBet(), WIDTH / 3 - 15, HEIGHT / 4 + 15);
    }

    public long getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(long currentBet) {
        this.currentBet = currentBet;
    }

    public List<Card> getTable() {
        return table;
    }

    public TexasHoldemPlayer getWinner() {
        return winner;
    }

    public void setWinner(TexasHoldemPlayer winner) {
        this.winner = winner;
    }

    public List<TexasHoldemPlayer> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(List<TexasHoldemPlayer> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }
}
