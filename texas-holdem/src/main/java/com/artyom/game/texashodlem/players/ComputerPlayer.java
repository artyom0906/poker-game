package com.artyom.game.texashodlem.players;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.texashodlem.cards.Card;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

import static com.artyom.game.texashodlem.TexasHoldem.HEIGHT;
import static com.artyom.game.texashodlem.TexasHoldem.WIDTH;


public class ComputerPlayer extends Player {


    private final PlayerPosition position;
    public ComputerPlayer(GameManager game, Map<String, Object> params, PlayerPosition position) {
        super(game, params);
        this.position = position;
    }

    PlayerEventHandler handler;

    @Override
    public void giveCards(List<Card> cards) {
        super.giveCards(cards);
        this.getCards().get(0).setX(-Card.SPRITE_SCALE_X+30);
    }

    @Override
    public void onEvent(PlayerEventHandler handler) {
        this.handler = handler;
    }

    @Override
    public void update(Input input) {
        if(isActive())
            handler.handle(new TexasHoldemEvent(this, "call"));
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.translate(WIDTH/2d, HEIGHT/2d);
        transform.rotate(switch (position){
            case TOP -> Math.PI;
            case LEFT -> Math.PI/2;
            case RIGHT -> Math.PI/2+Math.PI;
        });
        if(position==PlayerPosition.TOP) {
            transform.translate(0,HEIGHT/2d -(Card.SPRITE_SCALE_Y+10));
        } else {
            transform.translate(0, WIDTH/2d -(Card.SPRITE_SCALE_X+50));
        }

        g.setTransform(transform);
        g.setColor(Color.BLACK);
        g.drawString("chips: " + this.getChips(), -200, 100);
        this.getCards().forEach(card -> {
            card.render(g);
        });
        g.setTransform(new AffineTransform());
    }
}
