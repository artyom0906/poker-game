package com.artyom.game.draughts;

import com.artyom.game.api.Entity;
import com.artyom.game.api.Input;
import com.artyom.game.draughts.checker.Checker;

import java.awt.*;
import java.util.List;

public class Board extends Entity {

    public static final int BOARD_SQUARE_SCALE = 70;
    public static final int MARGIN_LEFT = 400-BOARD_SQUARE_SCALE*4;
    public static final int MARGIN_TOP = 300-BOARD_SQUARE_SCALE*4;

    private List<Checker> checkers;

    public Board(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i%2==j%2)
                    g.setColor(new Color(0xEEEED2));
                else
                    g.setColor(new Color(0x769756));
                g.fillRect(MARGIN_LEFT+i*BOARD_SQUARE_SCALE, MARGIN_TOP+j*BOARD_SQUARE_SCALE, BOARD_SQUARE_SCALE, BOARD_SQUARE_SCALE);
            }
        }
    }
}
