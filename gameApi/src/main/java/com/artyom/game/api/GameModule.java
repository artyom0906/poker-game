package com.artyom.game.api;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface GameModule {
    public ModuleConfiguration load();
    public GameComponents run();
    public void unload();
    public ConfigComponent getConfigurationPage(ActionListener listener);
}
