package com.artyom.game.api;

import static java.awt.event.MouseEvent.NOBUTTON;

public class Input {
    private int clickedButton = NOBUTTON;
    private int mouseX, mouseY;

    public Input(com.artyom.game.engine.IO.Input input) {
        this.clickedButton = input.getClickedButton();
        this.mouseX = input.getMouseX();
        this.mouseY = input.getMouseY();
    }

    public int getClickedButton() {
        return clickedButton;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void updateStatus() {
        clickedButton = NOBUTTON;
    }
}
