package com.artyom.game.draughts.graphics.checker;

import com.artyom.game.api.*;
import com.artyom.game.api.Button;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.SpriteSheet;
import com.artyom.game.api.graphics.TextureAtlas;
import com.artyom.game.draughts.graphics.board.BoardRenderable;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckerRenderable implements Renderable {

    private Sprite sprite;
    public static final int	SPRITE_SCALE_X = 110;
    public static final int	SPRITE_SCALE_Y = 110;
    private static final TextureAtlas textureAtlas = new TextureAtlas(CheckerRenderable.class.getClassLoader().getResourceAsStream("checkers.png"));
    private boolean selected = false;

    private Checker checker;


    public CheckerRenderable(Checker checker) {
        this.checker = checker;
        BufferedImage image = textureAtlas.cut((this.checker.getColor() == CheckerColor.WHITE?0:1)*SPRITE_SCALE_X, 0, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.sprite = new Sprite(sheet, 0.55f);
    }



    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        if(selected){
            g.setColor(new Color(0xbaca44));
            g.fillRect(
                    BoardRenderable.MARGIN_LEFT + (int) this.checker.getPoint().getX()* BoardRenderable.BOARD_SQUARE_SCALE,
                    BoardRenderable.MARGIN_TOP  + (int) this.checker.getPoint().getY()* BoardRenderable.BOARD_SQUARE_SCALE,
                    BoardRenderable.BOARD_SQUARE_SCALE,
                    BoardRenderable.BOARD_SQUARE_SCALE
            );
        }
        sprite.render(g,
                BoardRenderable.MARGIN_LEFT+(float) this.checker.getPoint().getX()* BoardRenderable.BOARD_SQUARE_SCALE+5,
                BoardRenderable.MARGIN_TOP + (float) this.checker.getPoint().getY()*BoardRenderable.BOARD_SQUARE_SCALE+5
        );

    }

    public Checker getChecker() {
        return checker;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
