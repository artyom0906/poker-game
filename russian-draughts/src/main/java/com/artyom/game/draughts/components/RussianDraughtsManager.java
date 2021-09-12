package com.artyom.game.draughts.components;

import com.artyom.game.api.*;
import com.artyom.game.draughts.board.Board;
import com.artyom.game.draughts.board.BoardRenderable;
import com.artyom.game.draughts.state.GameStart;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RussianDraughtsManager extends GameManager{
    private final Board board = new Board();


    public RussianDraughtsManager() {
        super(new GameStart(), new ArrayList<>());
    }

    public Board getBoard() {
        return board;
    }
}
