package com.artyom.game.engine.display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.*;

import com.artyom.game.api.ConfigComponent;
import com.artyom.game.api.GameModule;
import com.artyom.game.engine.IO.Input;

public abstract class Display {

	private static boolean			created	= false;
	private static JFrame			window;
	private static Canvas			content;

	private static BufferedImage	buffer;
	private static int[]			bufferData;
	private static Graphics			bufferGraphics;
	private static int				clearColor;

	private static BufferStrategy	bufferStrategy;

	public static void create(int width, int height, String title, int _clearColor, int numBuffers, GameModule module) {

		if (created)
			return;

		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = new Canvas();

		Dimension size = new Dimension(width, height);
		content.setPreferredSize(size);

		window.setResizable(false);
		JComponent configurationPage = null;
		configurationPage = module.getConfigurationPage(e -> {
			if(Objects.equals(e.getActionCommand(), "start")){
				for (Component component : window.getContentPane().getComponents()) {
					if(component instanceof ConfigComponent){
						window.getContentPane().remove(component);
					}
				}
				window.getContentPane().add(content);
				window.pack();
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				initBuf(width, height, _clearColor, numBuffers);
				System.out.println("start!!!");
			}
		});
		if(configurationPage!=null) {
			configurationPage.setPreferredSize(size);
			window.getContentPane().add(configurationPage);
		}else {
			window.getContentPane().add(content);
		}
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		if(configurationPage==null) {
			initBuf(width, height, _clearColor, numBuffers);
		}

	}

	private static void initBuf(int width, int height, int _clearColor, int numBuffers){
		buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
		bufferGraphics = buffer.getGraphics();
		((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		clearColor = _clearColor;

		content.createBufferStrategy(numBuffers);
		bufferStrategy = content.getBufferStrategy();
		created = true;
	}

	public static boolean isCreated() {
		return created;
	}

	public static void clear() {
		Arrays.fill(bufferData, clearColor);
	}

	public static void swapBuffers() {
		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(buffer, 0, 0, null);
		bufferStrategy.show();
	}

	public static Graphics2D getGraphics() {
		return (Graphics2D) bufferGraphics;
	}

	public static void destroy() {

		if (!created)
			return;

		window.dispose();

	}

	public static void setTitle(String title) {

		window.setTitle(title);

	}

	public static void addInputListener(Input inputListener, MouseListener listener) {
		content.addMouseListener( listener);
		window.add(inputListener);
	}

}
