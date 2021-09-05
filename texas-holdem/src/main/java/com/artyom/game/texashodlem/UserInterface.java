package com.artyom.game.texashodlem;



import com.artyom.game.api.*;
import com.artyom.game.api.Button;
import java.awt.*;

/*
Buttons:
    [Raise]
    [Call] is available if bet is raised
    [Fold]
*/
public class UserInterface extends Entity implements StateDependent {
    private Button raise;
    private Button call;
    private Button fold;
    protected UserInterface(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(Input input) {
        raise.update(input);
        call.update(input);
        fold.update(input);
    }

    @Override
    public void render(Graphics2D g) {
        raise.render(g);
        call.render(g);
        fold.render(g);
    }


    @Override
    public void nextState(GameState state) {

    }
}
