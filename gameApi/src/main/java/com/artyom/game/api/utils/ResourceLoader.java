package com.artyom.game.api.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ResourceLoader {

	public static BufferedImage loadImage(InputStream fileName) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}

}
