package com.artyom.game.draughts.components;

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
        RussianDraughtsManager russianDraughtsManager = new RussianDraughtsManager();

        DraughtsPlayer human = new HumanPlayer(russianDraughtsManager);
        DraughtsPlayer computer = new ComputerPlayer(russianDraughtsManager);

        return new GameComponents(russianDraughtsManager, new RussianDraughtsScreen(russianDraughtsManager));
    }

    @Override
    public void unload() {

    }

    @Override
    public JComponent getConfigurationPage() {
        return null;
    }
}

