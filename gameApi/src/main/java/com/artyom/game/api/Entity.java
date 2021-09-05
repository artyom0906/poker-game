package com.artyom.game.api;

import java.awt.Graphics2D;

public abstract class Entity {

	protected float			x;
	protected float			y;

	protected Entity( float x, float y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update(Input input);

	public abstract void render(Graphics2D g);

}
