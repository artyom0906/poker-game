package com.artyom.game.api;

import javax.swing.*;

public interface GameModule {
    public ModuleConfiguration load();
    public GameComponents run();
    public void unload();
    public JComponent getConfigurationPage();
}
