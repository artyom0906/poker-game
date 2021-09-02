package com.artyom.game.engine.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage	sheet;
	private int				spriteCount;
	private int				scaleX;
	private int				scaleY;
	private int				spritesInWidth;
	private int				spritesInHeight;

	public SpriteSheet(BufferedImage sheet, int spriteCount, int scaleX, int scaleY) {
		this.sheet = sheet;
		this.spriteCount = spriteCount;
		this.scaleX = scaleX;
		this.scaleY = scaleY;

		this.spritesInWidth = sheet.getWidth() / scaleX;
		this.spritesInHeight = sheet.getHeight() / scaleY;
	}

	public BufferedImage getSprite(int index) {

		index = index % spriteCount;

		int x = index % spritesInWidth * scaleX;
		int y = index / spritesInHeight * scaleY;

		return sheet.getSubimage(x, y, scaleX, scaleY);

	}

}
