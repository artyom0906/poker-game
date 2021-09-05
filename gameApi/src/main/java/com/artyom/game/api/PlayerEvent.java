package com.artyom.game.api;

public abstract class PlayerEvent {
    private final Player player;
    public PlayerEvent(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
