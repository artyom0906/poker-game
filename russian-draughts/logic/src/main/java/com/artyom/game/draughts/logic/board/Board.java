package com.artyom.game.draughts.logic.board;

import java.util.ArrayList;
import java.util.List;

public class Board<T> {
    public static final int BOARD_SQUARE_SCALE = 70;
    public static final int MARGIN_LEFT = 400-BOARD_SQUARE_SCALE*4;
    public static final int MARGIN_TOP = 300-BOARD_SQUARE_SCALE*4;

    private final List<T> pieces = new ArrayList<T>();

    public List<T> getPieces() {
        return pieces;
    }
}
