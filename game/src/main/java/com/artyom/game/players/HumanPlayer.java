package com.artyom.game.players;

import com.artyom.game.Game;
import com.artyom.game.cards.Card;
import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.Renderer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HumanPlayer extends Player{
    public HumanPlayer(Game game, int chips) {
        super(game, chips);
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
//
        //drawButton(g, 50, Renderer.HEIGHT-120, 80, 30, "call");

        g.setColor(Color.BLACK);
        g.setFont(Game.font);
        g.drawString("chips: " + this.getChips(), 50, Renderer.HEIGHT-30);


        AffineTransform transform = new AffineTransform();
        transform.translate(Renderer.WIDTH/2, Renderer.HEIGHT/2);
        transform.translate(0,Renderer.HEIGHT/2 -(Card.SPRITE_SCALE_Y+10));
        g.setTransform(transform);
        this.getCards().forEach(card -> {
            card.render(g);
        });
        g.setTransform(new AffineTransform());
    }

    private void drawButton(Graphics2D g, int x, int y, int width, int height, String text){
        g.setColor(new Color(0xC3C3C3));
        g.fill3DRect(x, y, width, height, true);
        for (int i = 1; i <= 2; i++)
            g.draw3DRect(x - i, y - i, width + 2 * i - 1, height + 2 * i - 1, true);
    }
}
