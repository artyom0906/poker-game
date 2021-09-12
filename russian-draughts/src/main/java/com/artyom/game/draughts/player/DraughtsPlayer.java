package com.artyom.game.draughts.player;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.Player;
import com.artyom.game.draughts.checker.Checker;

import java.util.List;


public abstract class DraughtsPlayer extends Player {
    public DraughtsPlayer(GameManager game) {
        super(game);
    }
}
