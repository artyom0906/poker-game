package com.artyom.game.texashodlem;



import com.artyom.game.api.*;
import com.artyom.game.api.Button;
import java.awt.*;
import java.util.concurrent.Callable;
import java.util.function.Function;

/*
Buttons:
    [Raise]
    [Call] is available if bet is raised
    [Fold]
*/
public class UserInterface extends Entity implements StateDependent {
    private final Button raise;
    private final Button call;
    private final Button fold;
    Function<String, String>  handler;
    public UserInterface(float x, float y) {
        super(x, y);
        raise = new Button(30, 560, 80, 30, "raise", true, ()->{
            handler.apply("raised");
        });
        call = new Button(30, 515, 80, 30, "call", true, ()->{
            handler.apply("call");
        });
        fold = new Button(690, 560, 80, 30, "fold", true, ()->{
            handler.apply("fold");
        });
    }

    public void setHandler(Function<String, String> handler){
        this.handler = handler;
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

    public void disable() {
        raise.setHidden(true);
        call.setHidden(true);
        fold.setHidden(true);
    }

    public void enable(){
        raise.setHidden(false);
        call.setHidden( false);
        fold.setHidden( false);
    }
}
