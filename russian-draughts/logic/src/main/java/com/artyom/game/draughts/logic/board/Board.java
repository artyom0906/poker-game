package com.artyom.game.draughts.logic.board;

import com.artyom.game.api.GameManager;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {


    private final RussianDraughtsManager manager;
    public Board(RussianDraughtsManager manager){
        this.manager = manager;
    }

    private final List<Checker> pieces = new ArrayList<>();

    public List<Checker> getPieces() {
        return pieces;
    }

    public void update(Checker checker, Point new_position) {

        System.out.println(pieces.size());
    }
}
