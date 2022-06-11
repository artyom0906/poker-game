package com.artyom.game.draughts.graphics.board;

import com.artyom.game.api.Button;
import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.Renderable;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.SpriteSheet;
import com.artyom.game.api.graphics.TextureAtlas;
import com.artyom.game.draughts.graphics.checker.CheckerRenderer;
import com.artyom.game.draughts.graphics.player.DraughtsPlayer;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.logic.state.GameEnd;
import com.artyom.game.draughts.logic.state.PlayerMove;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.artyom.game.draughts.graphics.checker.CheckerRenderer.SPRITE_SCALE_X;
import static com.artyom.game.draughts.graphics.checker.CheckerRenderer.SPRITE_SCALE_Y;

public class BoardRenderer implements Renderable {

    public static final int BOARD_SQUARE_SCALE = 70;
    public static final int MARGIN_LEFT = 400 - BOARD_SQUARE_SCALE * 4;
    public static final int MARGIN_TOP = 300 - BOARD_SQUARE_SCALE * 4;

    private static final TextureAtlas textureAtlas = new TextureAtlas(CheckerRenderer.class.getClassLoader().getResourceAsStream("checkers.png"));
    private final Sprite spriteWhite, spriteBlack;
    private final RussianDraughtsManager manager;

    private final Button restart;

    public BoardRenderer(RussianDraughtsManager manager) {
        this.manager = manager;
        BufferedImage image = textureAtlas.cut(0, 0, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        SpriteSheet sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.spriteWhite = new Sprite(sheet, 0.55f);
        image = textureAtlas.cut(SPRITE_SCALE_X, 0, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        sheet = new SpriteSheet(image, 1, SPRITE_SCALE_X, SPRITE_SCALE_Y);
        this.spriteBlack = new Sprite(sheet, 0.55f);

        restart = new Button((float) (BoardRenderer.MARGIN_LEFT + 8 * BoardRenderer.BOARD_SQUARE_SCALE + 25),
                (float) (BoardRenderer.MARGIN_TOP + 5.5 * BoardRenderer.BOARD_SQUARE_SCALE - 15), 70, 30,
                "restart", false, () -> {
            manager.nextState();
            manager.doAction();
            manager.getPlayers().stream().map(player -> (DraughtsPlayer)player).forEach(draughtsPlayer -> {
                draughtsPlayer.setCheckers(manager.getBoard().getPieces(draughtsPlayer.getColor()));
            });
        });
        restart.setHidden(true);
    }

    @Override
    public void init(GameInputRegistry registry) {
        restart.init(registry);
    }

    @Override
    public void render(Graphics2D g) {


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == j % 2)
                    g.setColor(new Color(0xEEEED2));
                else
                    g.setColor(new Color(0x769756));
                g.fillRect(MARGIN_LEFT + i * BOARD_SQUARE_SCALE, MARGIN_TOP + j * BOARD_SQUARE_SCALE,
                        BOARD_SQUARE_SCALE, BOARD_SQUARE_SCALE);
            }
        }

        if (manager.getState() instanceof PlayerMove move) {
            (move.getColor() == CheckerColor.BLACK ? spriteBlack : spriteWhite).render(g,
                    (float) (BoardRenderer.MARGIN_LEFT + 8 * BoardRenderer.BOARD_SQUARE_SCALE + 25),
                    (float) (BoardRenderer.MARGIN_TOP + 3.5 * BoardRenderer.BOARD_SQUARE_SCALE + 5)
            );
        } else if (manager.getState() instanceof GameEnd move) {
            restart.setHidden(false);
            restart.render(g);
            g.setColor(Color.RED);
            g.drawString("winner:",
                    (float) (BoardRenderer.MARGIN_LEFT + 8 * BoardRenderer.BOARD_SQUARE_SCALE + 35),
                    (float) (BoardRenderer.MARGIN_TOP + 3.5 * BoardRenderer.BOARD_SQUARE_SCALE - 15));
            (move.getWinner() == CheckerColor.BLACK ? spriteBlack : spriteWhite).render(g,
                    (float) (BoardRenderer.MARGIN_LEFT + 8 * BoardRenderer.BOARD_SQUARE_SCALE + 25),
                    (float) (BoardRenderer.MARGIN_TOP + 3.5 * BoardRenderer.BOARD_SQUARE_SCALE + 5)
            );
        }
    }
}
