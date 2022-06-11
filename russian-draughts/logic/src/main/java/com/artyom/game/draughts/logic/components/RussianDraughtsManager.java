package com.artyom.game.draughts.logic.components;

import com.artyom.game.api.*;
import com.artyom.game.draughts.logic.board.Board;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.state.GameStart;

import java.util.ArrayList;

public class RussianDraughtsManager extends GameManager{
    private final Board board = new Board(this);

    public RussianDraughtsManager() {
        super(new GameStart(), new ArrayList<>());
        doAction();
    }


    public Board getBoard() {
        return board;
    }
}
