package com.artyom.game.api;


public abstract class Player extends Entity {


	private final GameManager game;

	public Player(GameManager game) {
		super(0, 0);
		this.game = game;
	}

	public interface PlayerEventHandler{
		void handle(PlayerEvent call);
	}

	public GameManager getGame() {
		return game;
	}
}
