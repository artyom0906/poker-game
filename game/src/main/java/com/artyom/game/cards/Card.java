package com.artyom.game.cards;

import com.artyom.game.Entity;
import com.artyom.game.EntityType;
import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.graphics.Sprite;
import com.artyom.game.engine.graphics.SpriteSheet;
import com.artyom.game.engine.graphics.TextureAtlas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Card extends Entity {
    private final Rank rank;
    private final Suit suit;
    private Sprite sprite;

    public static final int	SPRITE_SCALE_X		= 81;
    public static final int	SPRITE_SCALE_Y		= 117;
    private static final TextureAtlas textureAtlas = new TextureAtlas(Card.class.getClassLoader().getResource("img.png").getFile());
    private boolean hidden = false;

    public Card(Rank rank, Suit suit, boolean hidden) {
        super(EntityType.CARD, 0, 0);
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
        if(!hidden) {
            BufferedImage image = textureAtlas.cut(rank.getId()*SPRITE_SCALE_X, suit.getId()*SPRITE_SCALE_Y, SPRITE_SCALE_X, SPRITE_SCALE_Y);
            SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
            this.sprite = new Sprite(sheet, 1);
        } else {
            BufferedImage image = textureAtlas.cut(0, 4*SPRITE_SCALE_Y, SPRITE_SCALE_X, SPRITE_SCALE_Y);
            SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
            this.sprite = new Sprite(sheet, 1);
        }
    }
    public Card(Rank rank, Suit suit) {
        this(rank, suit, true);
    }
    public void show(){
        this.hidden = false;
        BufferedImage image = textureAtlas.cut(rank.getId()*SPRITE_SCALE_X, suit.getId()*SPRITE_SCALE_Y, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.sprite = new Sprite(sheet, 1);
    }

    @Override
    public void update(Input input) {

    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    @Override
    public void render(Graphics2D g) {
        sprite.render(g, x, y);
    }

    boolean FindPoint(int x1, int y1, int x2, int y2, int x, int y)
    {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }
}
