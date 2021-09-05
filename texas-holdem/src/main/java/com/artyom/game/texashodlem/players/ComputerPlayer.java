package com.artyom.game.texashodlem.players;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.api.Player;
import com.artyom.game.api.cards.Card;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Map;

import static com.artyom.game.texashodlem.players.HumanPlayer.HEIGHT;
import static com.artyom.game.texashodlem.players.HumanPlayer.WIDTH;

public class ComputerPlayer extends Player {


    private final PlayerPosition position;
    public ComputerPlayer(GameManager game, Map<String, Object> params, PlayerPosition position) {
        super(game, params);
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
        transform.translate(WIDTH/2, HEIGHT/2);
        transform.rotate(switch (position){
            case TOP -> Math.PI;
            case LEFT -> Math.PI/2;
            case RIGHT -> Math.PI/2+Math.PI;
        });
        if(position==PlayerPosition.TOP) {
            transform.translate(0,HEIGHT/2 -(Card.SPRITE_SCALE_Y+10));
        } else {
            transform.translate(0, WIDTH/2 -(Card.SPRITE_SCALE_X+50));
        }

        g.setTransform(transform);
        this.getCards().forEach(card -> {
            card.render(g);
        });
        g.setTransform(new AffineTransform());
    }
}
