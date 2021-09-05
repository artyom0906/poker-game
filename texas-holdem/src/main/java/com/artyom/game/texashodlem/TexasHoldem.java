package com.artyom.game.texashodlem;

import com.artyom.game.api.*;
import com.artyom.game.api.cards.Card;
import com.artyom.game.api.cards.Deck;
import com.artyom.game.texashodlem.state.DealCardsToPlayers;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.artyom.game.texashodlem.players.HumanPlayer.HEIGHT;
import static com.artyom.game.texashodlem.players.HumanPlayer.WIDTH;

public class TexasHoldem extends GameManager implements GameScreen {

    private GameState state = new DealCardsToPlayers();
    private final Deck deck;

    private final int DEFAULT_CHIPS = 1000;
    public static Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\matro\\Documents\\kursach\\poker-game\\game\\src\\main\\resources\\W95FA.otf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Card> table;

    public TexasHoldem(){
        super(new DealCardsToPlayers(), new ArrayList<>());
        this.table = new ArrayList<>();
        deck = new Deck();
        deck.shuffle();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        ge.registerFont(font);
    }

    public GameState getState(){return state;}

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }

    public void doAction() {state.doAction(this);}

    public void setState(GameState state) {
        this.state = state;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setTable(List<Card> table){
        this.table = table;
    }

    public void showCards(int num){
        table.stream().limit(num).forEach(Card::show);
    }

    @Override
    public void init() {
        System.out.println("hello world");
        this.doAction();
        this.nextState();
        this.nextState();
        this.doAction();
    }

    @Override
    public void update(Input input) {
        this.getPlayers().forEach(player -> player.update(input));
    }

    @Override
    public void render(Graphics2D graphics)
    {
        if(!table.isEmpty()) {
            AffineTransform transform = new AffineTransform();
            transform.translate(WIDTH / 2, HEIGHT / 2);
            transform.translate(0, -Card.SPRITE_SCALE_Y / 2);
            graphics.setTransform(transform);
            this.table.forEach(card -> {
                card.render(graphics);
            });
            graphics.setTransform(new AffineTransform());
        }
        this.getPlayers().forEach(player -> player.render(graphics));
    }

}
