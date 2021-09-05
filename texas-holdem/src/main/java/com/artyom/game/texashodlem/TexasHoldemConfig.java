package com.artyom.game.texashodlem;

import com.artyom.game.api.GameManager;
import com.artyom.game.api.GameScreen;
import com.artyom.game.api.ModuleConfiguration;

import java.util.List;

public class TexasHoldemConfig implements ModuleConfiguration {


    public TexasHoldemConfig(){
    }

    @Override
    public int maxPlayers() {
        return 4;
    }

    @Override
    public String getImage() {
        return "texas-holdem.png";
    }

    @Override
    public String getName() {
        return "Texas Hold 'em";
    }

}
