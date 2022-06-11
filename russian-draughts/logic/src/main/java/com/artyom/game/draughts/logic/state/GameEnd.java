package com.artyom.game.draughts.logic.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.draughts.logic.checker.CheckerColor;

public class GameEnd implements GameState {

    CheckerColor winner;

    public GameEnd(CheckerColor color){
        this.winner = color;
    }
    @Override
    public void next(GameManager game) {
        game.setState(new GameStart());
    }

    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {
        System.out.println("game end");
    }

    public CheckerColor getWinner() {
        return winner;
    }
}
