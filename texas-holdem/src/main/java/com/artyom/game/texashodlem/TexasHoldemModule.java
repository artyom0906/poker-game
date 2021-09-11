package com.artyom.game.texashodlem;

import com.artyom.game.api.GameComponents;
import com.artyom.game.api.GameModule;
import com.artyom.game.api.ModuleConfiguration;
import com.artyom.game.texashodlem.players.ComputerPlayer;
import com.artyom.game.texashodlem.players.HumanPlayer;
import com.artyom.game.texashodlem.players.PlayerPosition;
import com.artyom.game.texashodlem.players.TexasHoldemPlayer;

import javax.swing.*;

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
        TexasHoldemPlayer south = new HumanPlayer(texasHoldem, 100L);
        TexasHoldemPlayer east = new ComputerPlayer(texasHoldem, 1000L, PlayerPosition.RIGHT);
        TexasHoldemPlayer north = new ComputerPlayer(texasHoldem, 1000L, PlayerPosition.TOP);
        TexasHoldemPlayer west = new ComputerPlayer(texasHoldem, 1000L, PlayerPosition.LEFT);

        south.setLeft(west);
        south.setRight(east);

        west.setLeft(north);
        west.setRight(south);

        north.setLeft(east);
        north.setRight(west);

        east.setLeft(south);
        east.setRight(north);

        texasHoldem.getPlayers().add(south);
        texasHoldem.getPlayers().add(east);
        texasHoldem.getPlayers().add(north);
        texasHoldem.getPlayers().add(west);

        texasHoldem.getPlayers().forEach(player -> {
            if (player instanceof TexasHoldemPlayer texasHoldemPlayer)
                texasHoldem.getCurrentPlayers().add(texasHoldemPlayer);
        });

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
