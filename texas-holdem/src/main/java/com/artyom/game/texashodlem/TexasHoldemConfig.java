package com.artyom.game.texashodlem;

import com.artyom.game.api.ModuleConfiguration;

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
