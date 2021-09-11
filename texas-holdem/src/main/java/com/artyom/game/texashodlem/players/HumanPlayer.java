package com.artyom.game.texashodlem.players;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.texashodlem.TexasHoldem;
import com.artyom.game.texashodlem.cards.Card;
import com.artyom.game.texashodlem.UserInterface;
import com.artyom.game.texashodlem.exceptions.NotEnoughChipsException;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import static com.artyom.game.texashodlem.TexasHoldem.HEIGHT;
import static com.artyom.game.texashodlem.TexasHoldem.WIDTH;

public class HumanPlayer extends TexasHoldemPlayer {

    UserInterface userInterface = new UserInterface((TexasHoldem)this.getGame(),0, 0);
    PlayerEventHandler handler;
    private Exception exception;
    private int timeout;

    public HumanPlayer(GameManager game, long chips) {
        super(game, chips);
    }

    @Override
    public void update(Input input) {
        this.getCards().forEach(card -> card.update(input));
        if (!this.isActive()) {
            this.userInterface.disable();
        } else {
            this.userInterface.enable();
        }
        userInterface.update(input);
        if (exception != null && timeout >= 0) {
            timeout--;
        } else {
            exception = null;
        }
    }

    @Override
    public void giveCards(List<Card> cards) {
        super.giveCards(cards);
        this.getCards().get(0).setX(-Card.SPRITE_SCALE_X + 30);
        this.getCards().forEach(Card::show);
    }

    @Override
    public void takeChips(long chips) throws NotEnoughChipsException {
        try {
            super.takeChips(chips);
        } catch (Exception e) {
            this.exception = e;
            timeout = 30;
            throw e;
        }
    }

    @Override
    public void onEvent(PlayerEventHandler handler) {
        this.handler = handler;
        userInterface.setHandler(event -> {
            handler.handle(new TexasHoldemEvent(this, event));
            return null;
        });
    }

    @Override
    public void render(Graphics2D g) {
        userInterface.render(g);

        AffineTransform transform = new AffineTransform();
        transform.translate(WIDTH / 2d, HEIGHT / 2d);
        transform.translate(0, HEIGHT / 2d - (Card.SPRITE_SCALE_Y + 10));
        g.setTransform(transform);
        g.setColor(Color.BLACK);
        g.drawString("chips: " + this.getChips(), -200, 100);
        if (exception != null) {
            g.setColor(Color.RED);
            g.drawString(this.exception.getMessage(), -230, 60);
        }
        this.getCards().forEach(card -> {
            card.render(g);
        });
        g.setTransform(new AffineTransform());
    }
}
