package com.artyom.game.texashodlem.cards;

import com.artyom.game.api.Entity;
import com.artyom.game.api.Input;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.SpriteSheet;
import com.artyom.game.api.graphics.TextureAtlas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

public class Card implements Entity {
    private final Rank rank;
    private final Suit suit;
    private Sprite sprite;

    public static final int	SPRITE_SCALE_X		= 81;
    public static final int	SPRITE_SCALE_Y		= 117;
    private static final TextureAtlas textureAtlas = new TextureAtlas(Card.class.getClassLoader().getResourceAsStream("img.png"));
    private float x;
    private float y;

    /**
     * @param rank Rank of card
     * @param suit Suit of card
     * @param hidden if hidden - set sprite to the back of the card, otherwise set sprite to the card itself
     */
    public Card(Rank rank, Suit suit, boolean hidden) {
        this.rank = rank;
        this.suit = suit;

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

    /**
     * @param enumType Type to get
     * @param <T> enumType
     * @return value of field of type enumType
     * @throws IllegalAccessException if field is a private field of a different class. Supposed to never happen
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> T getGetParam(Class<T> enumType) throws IllegalAccessException {
        for (Field field : this.getClass().getDeclaredFields()) {
            if(field.getType().equals(enumType)){
                return (T) field.get(this);
            }
        }
        return null;
    }
}
