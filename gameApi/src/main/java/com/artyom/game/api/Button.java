package com.artyom.game.api;


import java.awt.*;
import java.awt.event.MouseEvent;

public class Button extends Entity{
    private Runnable action;
    private final int width, height;
    private boolean hidden;
    private  String text;

    protected Button(float x, float y, int width, int height, String text, boolean enabled, Runnable action) {
        super(x, y);
        this.action = action;
        this.width = width;
        this.height = height;
        this.text = text;
        this.hidden = !enabled;
    }

    @Override
    public void update(Input input) {
        if(input.getClickedButton() == MouseEvent.BUTTON1 && !hidden){
            if(this.FindPoint((int)x, (int)y, (int)x+width, (int)y+height, input.getMouseX(), input.getMouseY())){
                action.run();
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if(!hidden){
            drawButton(g, (int)x, (int)y, width, height, text);
        }
    }

    boolean FindPoint(int x1, int y1, int x2, int y2, int x, int y)
    {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    private void drawButton(Graphics2D g, int x, int y, int width, int height, String text){
        g.setColor(new Color(0xC3C3C3));
        g.fill3DRect(x, y, width, height, true);
        for (int i = 1; i <= 2; i++)
            g.draw3DRect(x - i, y - i, width + 2 * i - 1, height + 2 * i - 1, true);
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public void setText(String text) {
        this.text = text;
    }
}
