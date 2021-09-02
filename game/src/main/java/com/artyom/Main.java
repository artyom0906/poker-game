package com.artyom;

import com.artyom.game.Game;
import com.artyom.game.engine.Renderer;

public class Main {

	public static void main(String[] args) {
		Renderer poker = new Renderer(new Game());
		poker.start();
	}
}