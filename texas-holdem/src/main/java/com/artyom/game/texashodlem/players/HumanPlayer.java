package com.artyom.game.texashodlem.players;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Input;
import com.artyom.game.api.Player;
import com.artyom.game.api.cards.Card;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Map;

public class HumanPlayer extends Player {


    public static final int		WIDTH			= 800;
    public static final int		HEIGHT			= 600;


    public HumanPlayer(GameManager game, Map<String, Object> params) {
        super(game, params);
    }

    @Override
    public void update(Input input) {
        this.getCards().forEach(card -> card.update(input));
        this.getCards().forEach(Card::show);
    }

    @Override
    public void giveCards(List<Card> cards) {
        super.giveCards(cards);
        this.getCards().get(0).setX(-Card.SPRITE_SCALE_X+30);
    }

    @Override
    public void render(Graphics2D g) {


        //drawButton(g, 50, Renderer.HEIGHT-70, 80, 30, "pass");
        //drawButton(g, 50, Renderer.HEIGHT-120, 80, 30, "call");

        //g.setColor(Color.BLACK);
        //g.setFont(Game.font);
        //g.drawString("chips: " + this.getChips(), 50, Renderer.HEIGHT-30);


        AffineTransform transform = new AffineTransform();
        transform.translate(WIDTH/2, HEIGHT/2);
        transform.translate(0,HEIGHT/2 -(Card.SPRITE_SCALE_Y+10));
        g.setTransform(transform);
        this.getCards().forEach(card -> {
            card.render(g);
        });
        g.setTransform(new AffineTransform());
    }

}
