package com.artyom.game.api.graphics;

import com.artyom.game.api.utils.ResourceLoader;

import java.awt.image.BufferedImage;
import java.io.InputStream;


public class TextureAtlas {

	BufferedImage	image;

	public TextureAtlas(InputStream imageName) {
		image = ResourceLoader.loadImage(imageName);
	}

	public BufferedImage cut(int x, int y, int w, int h) {
		try {
			return image.getSubimage(x, y, w, h);
		}catch (Exception e){
			System.out.printf("x: %d, y: %d, w: %d, h: %d\n", x, y, w, h);
		}
		return image.getSubimage(0,0, 52, 75);
	}

}
