package com.artyom.game.draughts.board;

import java.util.ArrayList;
import java.util.List;

public class Board extends Entity {

    public static final int BOARD_SQUARE_SCALE = 70;
    public static final int MARGIN_LEFT = 400-BOARD_SQUARE_SCALE*4;
    public static final int MARGIN_TOP = 300-BOARD_SQUARE_SCALE*4;

    private List<T> pieces = new ArrayList<T>();

    public List<T> getPieces() {
        return pieces;
    }
}
