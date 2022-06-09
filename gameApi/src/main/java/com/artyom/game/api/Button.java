package com.artyom.game.api;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

public class Button implements Entity{
    private Runnable action;
    private final int width, height;
    private boolean hidden;
    private  String text;
    private boolean clicked = false;
    private int clicked_time = 0;
    private float x;
    private float y;

    @Override
    public void init(GameInputRegistry registry) {
        registry.register(new InputHandler() {
            @Override
            public void mouseClicked(Point position, int button) {
                if(button == MouseEvent.BUTTON1 && !hidden){
                    if(findPoint((int)x, (int)y, (int)x+width, (int)y+height, position.x, position.y)){
                        clicked = true;
                        clicked_time = 10;
                        action.run();
                    }
                }else {
                    if(clicked_time>0){
                        clicked_time--;
                    }else {
                        clicked = false;
                    }
                }
            }
        });

    }

    public Button(float x, float y, int width, int height, String text, boolean enabled, Runnable action) {
        this.action = action;
        this.width = width;
        this.height = height;
        this.text = text;
        this.hidden = !enabled;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(Input input) {
    }

    @Override
    public void render(Graphics2D g) {
        if(!hidden){
            if(clicked)
                drawButton(g, (int)x, (int)y, width, height, text, 0xA3A3A3);
            else
                drawButton(g, (int)x, (int)y, width, height, text, 0xC3C3C3);
        }else {
            drawButton(g, (int)x, (int)y, width, height, text, 0x2C2C2C);
        }
    }

    public static boolean findPoint(int x1, int y1, int x2, int y2, int x, int y)
    {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    private void drawButton(Graphics2D g, int x, int y, int width, int height, String text, int color){
        g.setColor(new Color(color));
        g.fill3DRect(x, y, width, height, true);
        for (int i = 1; i <= 2; i++)
            g.draw3DRect(x - i, y - i, width + 2 * i - 1, height + 2 * i - 1, true);
        g.setFont(new Font("Courier", Font.BOLD,20));
        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(text, g);
        int x_ = (width - (int) r.getWidth()) / 2;
        int y_ = (height - (int) r.getHeight()) / 2 + fm.getAscent();
        g.drawString(text, x_+x, y_+y);
        //g.drawString(text, x, y);
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
