package com.artyom.game.texashodlem;

import com.artyom.game.api.GameComponents;
import com.artyom.game.api.GameModule;
import com.artyom.game.api.ModuleConfiguration;
import com.artyom.game.texashodlem.players.ComputerPlayer;
import com.artyom.game.texashodlem.players.HumanPlayer;
import com.artyom.game.texashodlem.players.PlayerPosition;

import javax.swing.*;
import java.util.Map;

public class TexasHoldemModule implements GameModule {
    public TexasHoldemModule(){

    }
    @Override
    public ModuleConfiguration load() {
        return new TexasHoldemConfig();
    }

    @Override
    public GameComponents run() {
        TexasHoldem texasHoldem = new TexasHoldem();
        texasHoldem.getPlayers().add(new HumanPlayer(texasHoldem, Map.of("chips", 1000L)));
        texasHoldem.getPlayers().add(new ComputerPlayer(texasHoldem, Map.of("chips", 1000L), PlayerPosition.TOP));
        texasHoldem.getPlayers().add(new ComputerPlayer(texasHoldem, Map.of("chips", 1000L), PlayerPosition.LEFT));
        texasHoldem.getPlayers().add(new ComputerPlayer(texasHoldem, Map.of("chips", 1000L), PlayerPosition.RIGHT));
        return new GameComponents(texasHoldem, texasHoldem);
    }

    @Override
    public void unload() {

    }

    @Override
    public JComponent getConfigurationPage() {
        return null;
    }
}
