package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.GameComponents;
import com.artyom.game.api.GameModule;
import com.artyom.game.api.ModuleConfiguration;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.logic.player.ComputerPlayer;
import com.artyom.game.draughts.logic.player.DraughtsPlayer;
import com.artyom.game.draughts.logic.player.HumanPlayer;

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

