package com.artyom.game;

import com.artyom.game.api.GameModule;
import com.artyom.game.api.ModuleConfiguration;

public record Game(GameModule module, ModuleConfiguration configuration) {
}
