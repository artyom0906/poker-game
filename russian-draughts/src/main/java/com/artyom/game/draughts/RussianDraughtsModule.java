package com.artyom.game.draughts;

import com.artyom.game.api.GameComponents;
import com.artyom.game.api.GameModule;
import com.artyom.game.api.ModuleConfiguration;
import com.artyom.game.draughts.player.ComputerPlayer;
import com.artyom.game.draughts.player.DraughtsPlayer;
import com.artyom.game.draughts.player.HumanPlayer;

import javax.swing.*;

public class RussianDraughtsModule implements GameModule {
    public RussianDraughtsModule(){

    }
    @Override
    public ModuleConfiguration load() {
        return new RussianDraughtsConfig();
    }

    @Override
    public GameComponents run() {
        RussianDraughts russianDraughts = new RussianDraughts();

        DraughtsPlayer human = new HumanPlayer(russianDraughts);
        DraughtsPlayer computer = new ComputerPlayer(russianDraughts);

        return new GameComponents(russianDraughts, russianDraughts);
    }

    @Override
    public void unload() {

    }

    @Override
    public JComponent getConfigurationPage() {
        return null;
    }
}

