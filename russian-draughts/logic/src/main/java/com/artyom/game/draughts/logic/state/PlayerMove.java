package com.artyom.game.draughts.logic.state;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameState;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;

public class PlayerMove implements GameState {
    private final CheckerColor color;

    public PlayerMove(CheckerColor color) {
        this.color = color;
    }

    @Override
    public void next(GameManager game) {
        if(game instanceof RussianDraughtsManager manager){

        }
        game.setState(new PlayerMove(color.next()));
    }
    @Override
    public void prev(GameManager game) {

    }

    @Override
    public void doAction(GameManager game) {

    }
}

