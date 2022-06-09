package com.artyom.game.draughts.logic.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;

public class GameStart implements GameState {
    @Override
    public void next(GameManager game) {
        game.setState(new PlayerMove(CheckerColor.WHITE));
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        game.nextState();
    }


}
