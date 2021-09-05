package com.artyom.game.texashodlem.cards;

import com.artyom.game.api.Entity;
import com.artyom.game.api.Input;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.SpriteSheet;
import com.artyom.game.api.graphics.TextureAtlas;

import java.awt.*;
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
        super(0, 0);
        this.rank = rank;
        this.suit = suit;
        this.hidden = hidden;
        if (!hidden) {
            BufferedImage image = textureAtlas.cut(rank.getId() * SPRITE_SCALE_X, suit.getId() * SPRITE_SCALE_Y, SPRITE_SCALE_X, SPRITE_SCALE_Y);
            SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
            this.sprite = new Sprite(sheet, 1);
        } else {
            BufferedImage image = textureAtlas.cut(0, 4 * SPRITE_SCALE_Y, SPRITE_SCALE_X, SPRITE_SCALE_Y);
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

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
}
