package com.artyom.game.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.artyom.game.api.cards.Card;

public abstract class Player extends Entity {

	private List<Card> cards = new ArrayList<>();
	private Map<String, Object> params;
	private final GameManager game;

	public Player(GameManager game, Map<String, Object> params) {
		super(0, 0);
		this.params = params;
		this.game = game;
	}

	public void giveCards(List<Card> cards){
		this.cards = cards;
	}

	//public void takeChips(int amount){
	//	if(chips<amount){
	//		throw new IllegalArgumentException("not enough chips");
	//	}
	//	chips-=amount;
	//}

	//public void giveChips(int amount){
	//	chips+=amount;
	//}

	//public int getChips() {return chips;}


	public Map<String, Object> getParams() {
		return params;
	}

	public List<Card> getCards() {
		return cards;
	}
}
