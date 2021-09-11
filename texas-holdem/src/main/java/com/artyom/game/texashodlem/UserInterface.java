package com.artyom.game.texashodlem;



import com.artyom.game.api.*;
import com.artyom.game.api.Button;
import com.artyom.game.texashodlem.players.TexasHoldemPlayer;
import com.artyom.game.texashodlem.state.EndRound;

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
    private final Button newRound;
    private final Button disconnect;
    private TexasHoldem texasHoldem;
    Function<String, String>  handler;
    public UserInterface(TexasHoldem texasHoldem, float x, float y) {
        super(x, y);
        this.texasHoldem = texasHoldem;
        raise = new Button(30, 560, 80, 30, "Raise", true, ()->{
            handler.apply("raised");
        });
        call = new Button(30, 515, 80, 30, "Call", true, ()->{
            handler.apply("call");
        });
        fold = new Button(690, 560, 80, 30, "Fold", true, ()->{
            handler.apply("fold");
        });
        newRound = new Button(540, 560, 130, 30, "New Round", true, ()->{
            handler.apply("newround");
        });
        disconnect = new Button(540, 515, 130, 30, "Disconnect", true, ()->{
            handler.apply("disconnect");
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
        newRound.update(input);
        disconnect.update(input);
    }

    @Override
    public void render(Graphics2D g) {
        raise.render(g);
        call.render(g);
        fold.render(g);
        if (texasHoldem.getState() instanceof EndRound) {
            newRound.render(g);
            disconnect.render(g);
        }
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
