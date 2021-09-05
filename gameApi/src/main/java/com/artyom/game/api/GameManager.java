package com.artyom.game.api;

import java.util.List;

public abstract class GameManager {
    private GameState state;
    private final List<Player> players;

    public GameManager(GameState state, List<Player> players){
        this.state = state;
        this.players = players;
    }

    public List<Player> getPlayers() {return players;}

    public void doAction() {state.doAction(this);}

    public void setState(GameState state) {this.state = state;}

    public void previousState() {
        state.prev(this);
    }

    public void nextState() {
        state.next(this);
    }
}
