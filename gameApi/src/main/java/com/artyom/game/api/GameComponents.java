package com.artyom.game.api;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameScreen;

public record GameComponents(GameManager manager, GameScreen screen) {
}
