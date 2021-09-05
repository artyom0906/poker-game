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
        TexasHoldemPlayer front = new HumanPlayer(texasHoldem, 100L);
        TexasHoldemPlayer right = new ComputerPlayer(texasHoldem, 1000L, PlayerPosition.RIGHT);
        TexasHoldemPlayer top = new ComputerPlayer(texasHoldem, 1000L, PlayerPosition.TOP);
        TexasHoldemPlayer left = new ComputerPlayer(texasHoldem, 1000L, PlayerPosition.LEFT);

        front.setLeft(left);
        //front.setRight(right);

        left.setLeft(top);
        left.setRight(front);

        top.setLeft(right);
        top.setRight(left);

        //right.setLeft(front);
        right.setRight(top);

        texasHoldem.getPlayers().add(front);
        texasHoldem.getPlayers().add(right);
        texasHoldem.getPlayers().add(top);
        texasHoldem.getPlayers().add(left);


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
