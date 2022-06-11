package com.artyom.game.api;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class ConfigComponent extends JPanel {
    protected final ActionListener listener;
    protected ConfigComponent(ActionListener listener){

        this.listener = listener;
    }
}
