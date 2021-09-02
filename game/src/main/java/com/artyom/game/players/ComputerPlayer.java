package com.artyom.game.players;

import com.artyom.game.Game;
import com.artyom.game.cards.Card;
import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ComputerPlayer extends Player{
    private final PlayerPosition position;
    public ComputerPlayer(Game game, int chips, PlayerPosition position) {
        super(game, chips);
        this.position = position;
    }

    @Override
    public void giveCards(List<Card> cards) {
        super.giveCards(cards);
        this.getCards().get(0).setX(-Card.SPRITE_SCALE_X+30);
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        transform.translate(Renderer.WIDTH/2, Renderer.HEIGHT/2);
        transform.rotate(switch (position){
            case TOP -> Math.PI;
            case LEFT -> Math.PI/2;
            case RIGHT -> Math.PI/2+Math.PI;
        });
        if(position==PlayerPosition.TOP) {
            transform.translate(0,Renderer.HEIGHT/2 -(Card.SPRITE_SCALE_Y+10));
        } else {
            transform.translate(0, Renderer.WIDTH/2 -(Card.SPRITE_SCALE_X+50));
        }

        g.setTransform(transform);
        this.getCards().forEach(card -> {
            card.render(g);
        });
        g.setTransform(new AffineTransform());
    }
}
