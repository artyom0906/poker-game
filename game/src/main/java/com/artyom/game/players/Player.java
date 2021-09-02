package com.artyom.game.players;

import java.util.ArrayList;
import java.util.List;

import com.artyom.game.Entity;
import com.artyom.game.EntityType;
import com.artyom.game.Game;
import com.artyom.game.cards.Card;

public abstract class Player extends Entity {

	private List<Card> cards = new ArrayList<>();
	private int chips;
	private final Game game;

	public Player(Game game, int chips) {
		super(EntityType.Player, 0, 0);
		this.chips = chips;
		this.game = game;
	}

	public void giveCards(List<Card> cards){
		this.cards = cards;
	}

	public void takeChips(int amount){
		if(chips<amount){
			throw new IllegalArgumentException("not enough chips");
		}
		chips-=amount;
	}

	public void giveChips(int amount){
		chips+=amount;
	}

	public int getChips() {
		return chips;
	}

	public List<Card> getCards() {
		return cards;
	}
}
