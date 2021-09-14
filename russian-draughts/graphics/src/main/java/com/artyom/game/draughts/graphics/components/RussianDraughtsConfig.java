package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.ModuleConfiguration;

public class RussianDraughtsConfig  implements ModuleConfiguration {

    @Override
    public int maxPlayers() {
        return 0;
    }

    @Override
    public String getImage() {
        return "img.png";
    }

    @Override
    public String getName() {
        return "russian-com.artyom.game.draughts";
    }
}
