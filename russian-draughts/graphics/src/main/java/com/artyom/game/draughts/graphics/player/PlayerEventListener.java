package com.artyom.game.draughts.graphics.player;


import com.artyom.game.draughts.graphics.checker.CheckerRenderer;

public interface PlayerEventListener {
    void handle(CheckerPlayerEvent event);
}
