package com.artyom.game;

import com.artyom.game.cards.Card;
import com.artyom.game.cards.Deck;
import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.Renderer;
import com.artyom.game.engine.api.Screen;
import com.artyom.game.players.ComputerPlayer;
import com.artyom.game.players.HumanPlayer;
import com.artyom.game.players.Player;
import com.artyom.game.players.PlayerPosition;
import com.artyom.game.texasholdem.DealCardsToPlayers;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
3-10 players
Round:
    1. Dealer give every player 2 cards.
    2. Betting round:
        They bet/call/pass/raise/fold in clockwise order.
        When every player passes, betting round ends.
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
public class Game  implements Screen {

    private GameState state = new DealCardsToPlayers();

    private final List<Player> players;
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

    public Game(){
        players = new ArrayList<>();
        deck = new Deck();
        deck.shuffle();
        players.add(new HumanPlayer(this,1000));
        players.add(new ComputerPlayer(this, 1000, PlayerPosition.TOP));
        players.add(new ComputerPlayer(this, 1000, PlayerPosition.LEFT));
        players.add(new ComputerPlayer(this, 1000, PlayerPosition.RIGHT));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        ge.registerFont(font);
    }

    public List<Player> getPlayers() {
        return players;
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
    public void init(Renderer game) {
        this.doAction();
        this.nextState();
        this.nextState();
        this.doAction();
    }

    @Override
    public void update(Input input) {
        players.forEach(player -> player.update(input));
    }

    @Override
    public void render(Graphics2D graphics)
    {
        AffineTransform transform = new AffineTransform();
        transform.translate(Renderer.WIDTH/2, Renderer.HEIGHT/2);
        transform.translate(0,-Card.SPRITE_SCALE_Y/2);
        graphics.setTransform(transform);
        this.table.forEach(card -> {
            card.render(graphics);
        });
        graphics.setTransform(new AffineTransform());

        players.forEach(player -> player.render(graphics));
    }

}
