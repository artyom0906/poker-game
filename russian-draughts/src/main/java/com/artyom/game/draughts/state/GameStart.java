package com.artyom.game.draughts.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.draughts.checker.Checker;
import com.artyom.game.draughts.checker.CheckerColor;
import com.artyom.game.draughts.components.RussianDraughtsManager;

public class GameStart implements GameState {
    @Override
    public void next(GameManager game) {

    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        if (game instanceof RussianDraughtsManager russianDraughts) {
            russianDraughts.getBoard().getPieces().addAll(Checker.builder(CheckerColor.BLACK).build());
            russianDraughts.getBoard().getPieces().addAll(Checker.builder(CheckerColor.WHITE).build());
        }
    }
}
