package com.artyom.game.engine;

import java.awt.Graphics2D;
import java.awt.event.MouseListener;

import com.artyom.game.engine.IO.Input;
import com.artyom.game.engine.api.Screen;
import com.artyom.game.engine.display.Display;
import com.artyom.game.engine.utils.Time;

public class Renderer implements Runnable {

	public static final int		WIDTH			= 800;
	public static final int		HEIGHT			= 600;
	public static final String	TITLE			= "Texas Holdem";
	public static final int		CLEAR_COLOR		= 0xff2c8257;
	public static final int		NUM_BUFFERS		= 3;

	public static final float	UPDATE_RATE		= 60.0f;
	public static final float	UPDATE_INTERVAL	= Time.SECOND / UPDATE_RATE;
	public static final long	IDLE_TIME		= 1;

	private boolean				running;
	private Thread				gameThread;
	private final Graphics2D	graphics;
	private final Input			input;
	private final MouseListener mouseListener;

	private final Screen screen;

	public Renderer(Screen screen, MouseListener mouseListener) {
		this.screen = screen;
		running = false;
		Display.create(WIDTH, HEIGHT, TITLE, CLEAR_COLOR, NUM_BUFFERS);
		graphics = Display.getGraphics();
		this.mouseListener = mouseListener;
		input = new Input();
		Display.addInputListener(input, mouseListener);
		screen.init(this);
	}

	public synchronized void start() {

		if (running)
			return;

		running = true;
		gameThread = new Thread(this);
		gameThread.start();

	}

	public synchronized void stop() {

		if (!running)
			return;

		running = false;

		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cleanUp();

	}

	private void update() {
		screen.update(input);
	}

	private void render() {
		Display.clear();
		screen.render(graphics);
		Display.swapBuffers();
	}

	public void run() {

		int fps = 0;
		int upd = 0;
		int updl = 0;

		long count = 0;

		float delta = 0;

		long lastTime = Time.get();
		while (running) {
			long now = Time.get();
			long elapsedTime = now - lastTime;
			lastTime = now;

			count += elapsedTime;

			boolean render = false;
			delta += (elapsedTime / UPDATE_INTERVAL);
			while (delta > 1) {
				update();
				upd++;
				delta--;
				if (render) {
					updl++;
				} else {
					render = true;
				}
			}

			if (render) {
				render();
				fps++;
			} else {
				try {
					Thread.sleep(IDLE_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (count >= Time.SECOND) {
				Display.setTitle(TITLE + " || Fps: " + fps + " | Upd: " + upd + " | Updl: " + updl);
				upd = 0;
				fps = 0;
				updl = 0;
				count = 0;
			}

		}

	}

	private void cleanUp() {
		Display.destroy();
	}

}
