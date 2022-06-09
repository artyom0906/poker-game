package com.artyom.game.api;


public abstract class Player implements Entity {


	private final GameManager game;

	public Player(GameManager game) {
		game.getPlayers().add(this);
		this.game = game;
	}

	public interface PlayerEventHandler{
		void handle(PlayerEvent call);
	}

	public GameManager getGame() {
		return game;
	}
}
