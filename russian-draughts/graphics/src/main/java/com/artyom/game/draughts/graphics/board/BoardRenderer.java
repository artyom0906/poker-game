package com.artyom.game.draughts.graphics.board;

import com.artyom.game.api.Renderable;

import java.awt.*;

public class BoardRenderer implements Renderable {

    public static final int BOARD_SQUARE_SCALE = 70;
    public static final int MARGIN_LEFT = 400-BOARD_SQUARE_SCALE*4;
    public static final int MARGIN_TOP = 300-BOARD_SQUARE_SCALE*4;


    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i%2==j%2)
                    g.setColor(new Color(0xEEEED2));
                else
                    g.setColor(new Color(0x769756));
                g.fillRect(MARGIN_LEFT+i*BOARD_SQUARE_SCALE, MARGIN_TOP+j*BOARD_SQUARE_SCALE,
                        BOARD_SQUARE_SCALE, BOARD_SQUARE_SCALE);
            }
        }
    }
}
