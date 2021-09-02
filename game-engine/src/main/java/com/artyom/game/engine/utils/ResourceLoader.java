package com.artyom.game.engine.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader {

	public static BufferedImage loadImage(String fileName) {

		BufferedImage image = null;

		try {
			File file = new File(fileName);
			System.out.print(file.getAbsolutePath());
			image = ImageIO.read(file);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}

}
