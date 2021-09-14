package com.artyom.game.draughts.graphics.checker;

import com.artyom.game.api.Button;
import com.artyom.game.api.Entity;
import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.Input;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.SpriteSheet;
import com.artyom.game.api.graphics.TextureAtlas;
import com.artyom.game.draughts.graphics.board.BoardRenderable;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CheckerRenderable extends Checker implements Entity {

    private Sprite sprite;
    public static final int	SPRITE_SCALE_X = 110;
    public static final int	SPRITE_SCALE_Y = 110;
    private static final TextureAtlas textureAtlas = new TextureAtlas(CheckerRenderable.class.getClassLoader().getResourceAsStream("checkers.png"));
    private boolean selected = false;


    public CheckerRenderable(Checker checker) {
        super(checker);
        BufferedImage image = textureAtlas.cut((this.color== CheckerColor.WHITE?0:1)*SPRITE_SCALE_X, 0, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.sprite = new Sprite(sheet, 0.55f);
    }

    @Override
    public void init(GameInputRegistry registry) {
        registry.register(this);
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        if(selected){
            g.setColor(new Color(0xbaca44));
            g.fillRect(
                    BoardRenderable.MARGIN_LEFT + (int) this.point.getX()* BoardRenderable.BOARD_SQUARE_SCALE,
                    BoardRenderable.MARGIN_TOP  + (int) this.point.getY()* BoardRenderable.BOARD_SQUARE_SCALE,
                    BoardRenderable.BOARD_SQUARE_SCALE,
                    BoardRenderable.BOARD_SQUARE_SCALE
            );
        }
        sprite.render(g,
                BoardRenderable.MARGIN_LEFT+(float) this.point.getX()* BoardRenderable.BOARD_SQUARE_SCALE+5,
                BoardRenderable.MARGIN_TOP + (float) this.point.getY()*BoardRenderable.BOARD_SQUARE_SCALE+5
        );

    }

    @Override
    public void mouseClicked(Point position, int button) {
        boolean onThis = Button.findPoint(
                BoardRenderable.MARGIN_LEFT + (int) this.point.getX()* BoardRenderable.BOARD_SQUARE_SCALE,
                BoardRenderable.MARGIN_TOP  + (int) this.point.getY()* BoardRenderable.BOARD_SQUARE_SCALE,
                BoardRenderable.MARGIN_LEFT + (int) this.point.getX()* BoardRenderable.BOARD_SQUARE_SCALE+BoardRenderable.BOARD_SQUARE_SCALE,
                BoardRenderable.MARGIN_TOP  + (int) this.point.getY()* BoardRenderable.BOARD_SQUARE_SCALE+BoardRenderable.BOARD_SQUARE_SCALE,
                position.x, position.y);
        if(onThis){
            selected = !selected;
        }
    }

    @Override
    public void mousePressed(Point point, int button) {
    }

    @Override
    public void mouseReleased(Point point, int button) {
    }
}
