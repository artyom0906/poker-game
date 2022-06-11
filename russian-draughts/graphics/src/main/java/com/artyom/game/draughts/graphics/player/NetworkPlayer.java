package com.artyom.game.draughts.graphics.player;

import com.artyom.game.api.GameManager;
import com.artyom.game.draughts.logic.checker.Checker;
import com.artyom.game.draughts.logic.checker.CheckerColor;
import com.artyom.game.draughts.logic.components.RussianDraughtsManager;
import com.artyom.game.network.connection.Message;
import com.artyom.game.network.connection.Network;
import com.artyom.game.network.connection.NetworkListener;

import java.util.Set;

public class NetworkPlayer extends DraughtsPlayer implements NetworkListener<EventType, CheckerPlayerEvent> {

    private final RussianDraughtsManager game;
    public NetworkPlayer(GameManager game, CheckerColor color, Set<Checker> checkers) {
        super(game, color, checkers);
        this.game = (RussianDraughtsManager) game;
    }

    @Override
    public void onMessage(Message<EventType, CheckerPlayerEvent> message) {
        Checker ch = null;
        System.out.println(message);
        if(message.message().position()!=null){
            ch = this.render.getCheckers().stream().filter(checker ->
                    (int)checker.getPoint().getY() == message.message().position().y &&
                    (int)checker.getPoint().getX() == message.message().position().x)
                    .findFirst().orElse(null);
        }
        switch (message.messageType()){
            case SelectChecker -> {
                render.setSelected(ch);
            }
            case MakeMove -> {
                game.getBoard().update(render.getSelected(), message.message().position());
            }
            case UnselectChecker -> {
                render.setSelected(null);
            }
        }
    }
}
