package com.artyom.game.texashodlem.players;

import com.artyom.game.api.Player;
import com.artyom.game.api.PlayerEvent;

public class TexasHoldemEvent extends PlayerEvent {
    private final String event;
    public TexasHoldemEvent(Player player, String event) {
        super(player);
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
