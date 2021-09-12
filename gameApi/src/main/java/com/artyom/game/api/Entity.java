package com.artyom.game.api;

import java.awt.Graphics2D;

public abstract class Entity implements Renderable{

	protected float			x;
	protected float			y;

	protected Entity( float x, float y) {
		this.x = x;
		this.y = y;
	}
}
