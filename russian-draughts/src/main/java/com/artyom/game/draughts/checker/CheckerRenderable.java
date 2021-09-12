package com.artyom.game.draughts.checker;

import com.artyom.game.api.Entity;
import com.artyom.game.api.GameInputRegistry;
import com.artyom.game.api.Input;
import com.artyom.game.api.graphics.Sprite;
import com.artyom.game.api.graphics.TextureAtlas;

import java.awt.*;

public class CheckerRenderable extends Checker implements Entity {

    private Sprite sprite;
    public static final int	SPRITE_SCALE_X		= 81;
    public static final int	SPRITE_SCALE_Y		= 117;
    private static final TextureAtlas textureAtlas = new TextureAtlas(CheckerRenderable.class.getClassLoader().getResourceAsStream("img.png"));

    public CheckerRenderable(Checker checker) {
        super(checker);
    }

    @Override
    public void init(GameInputRegistry registry) {
        registry.register(this);
    }

    @Override
    public void update(Input input) {

    }

    @Override
    public void render(Graphics2D g) {

    }

    @Override
    public void mouseClicked(Point position, int button) {
    }

    @Override
    public void mousePressed(Point point, int button) {
    }

    @Override
    public void mouseReleased(Point point, int button) {
    }
}
