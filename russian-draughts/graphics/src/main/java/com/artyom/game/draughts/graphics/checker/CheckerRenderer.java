package com.artyom.game.draughts.graphics.checker;

import com.artyom.game.api.*;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.SpriteSheet;
import com.artyom.game.api.graphics.TextureAtlas;
import com.artyom.game.draughts.graphics.board.BoardRenderer;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Set;

public class CheckerRenderer implements Renderable {

    private Sprite sprite;
    private Sprite kingSprite;
    public static final int	SPRITE_SCALE_X = 110;
    public static final int	SPRITE_SCALE_Y = 110;
    private static final TextureAtlas textureAtlas = new TextureAtlas(CheckerRenderer.class.getClassLoader().getResourceAsStream("checkers.png"));
    private Checker selected;

    private Set<Checker> checkers;


    public CheckerRenderer(Set<Checker> checkers) {
        this.checkers = checkers;
        BufferedImage image = textureAtlas.cut((this.checkers.stream().findAny().get().getColor() == CheckerColor.WHITE?0:1)*SPRITE_SCALE_X, 0, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.sprite = new Sprite(sheet, 0.55f);

        BufferedImage image1 = textureAtlas.cut(
                (this.checkers.stream().findAny().get().getColor() == CheckerColor.WHITE?0:1)*SPRITE_SCALE_X,
                SPRITE_SCALE_Y, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        SpriteSheet sheet1 = new SpriteSheet(image1, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.kingSprite = new Sprite(sheet1, 0.55f);

    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        checkers.stream().filter(Checker::isActive).forEach(checker -> {
            if (checker.equals(selected)) {
                g.setColor(new Color(0xbaca44));
                g.fillRect(
                        BoardRenderer.MARGIN_LEFT + (int) checker.getPoint().getX() * BoardRenderer.BOARD_SQUARE_SCALE,
                        BoardRenderer.MARGIN_TOP + (int) checker.getPoint().getY() * BoardRenderer.BOARD_SQUARE_SCALE,
                        BoardRenderer.BOARD_SQUARE_SCALE,
                        BoardRenderer.BOARD_SQUARE_SCALE
                );
            }
            if(checker.isKing()){
                kingSprite.render(g,
                        BoardRenderer.MARGIN_LEFT + (float) checker.getPoint().getX() * BoardRenderer.BOARD_SQUARE_SCALE + 5,
                        BoardRenderer.MARGIN_TOP + (float) checker.getPoint().getY() * BoardRenderer.BOARD_SQUARE_SCALE + 5
                );
            }else {
                sprite.render(g,
                        BoardRenderer.MARGIN_LEFT + (float) checker.getPoint().getX() * BoardRenderer.BOARD_SQUARE_SCALE + 5,
                        BoardRenderer.MARGIN_TOP + (float) checker.getPoint().getY() * BoardRenderer.BOARD_SQUARE_SCALE + 5
                );
            }
        });
    }

    public Set<Checker> getCheckers() {
        return checkers;
    }

    public Checker getSelected() {
        return selected;
    }

    public void setSelected(Checker selected) {
        this.selected = selected;
    }
}
