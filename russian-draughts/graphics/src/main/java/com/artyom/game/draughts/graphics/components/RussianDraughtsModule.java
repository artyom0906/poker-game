package com.artyom.game.draughts.graphics.components;

import com.artyom.game.api.*;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.draughts.graphics.player.HumanPlayer;

import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class RussianDraughtsModule implements GameModule {

    private RussianDraughtsManager russianDraughtsManager;
    private RussianDraughtsScreen screen;
    public RussianDraughtsModule(){

    }
    @Override
    public ModuleConfiguration load() {
        return new RussianDraughtsConfig();
    }

    @Override
    public GameComponents run() {
        russianDraughtsManager = new RussianDraughtsManager();
        screen = new RussianDraughtsScreen(russianDraughtsManager);
        return new GameComponents(russianDraughtsManager, screen);
    }

    @Override
    public void unload() {

    }

    @Override
    public ConfigComponent getConfigurationPage(ActionListener listener) {
        return new ConfigMenu(listener, russianDraughtsManager, screen);
    }

}

