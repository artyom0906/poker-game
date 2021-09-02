package com.artyom.game.engine.IO;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import static java.awt.event.MouseEvent.NOBUTTON;

public class Input extends JComponent implements MouseListener {

	private boolean[]	map;
	private int clickedButton = NOBUTTON;
	private int mouseX, mouseY;

	public Input() {
		map = new boolean[256];
		for (int i = 0; i < map.length; i++) {
			final int KEY_CODE = i;
			getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, false), i * 2);
			getActionMap().put(i * 2, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					map[KEY_CODE] = true;
				}
			});

			getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i, 0, true), i * 2 + 1);
			getActionMap().put(i * 2 + 1, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					map[KEY_CODE] = false;
				}
			});

		}
	}

	public boolean[] getMap() {
		return Arrays.copyOf(map, map.length);
	}

	public boolean getKey(int keyCode) {
		return map[keyCode];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		clickedButton = e.getButton();
		mouseX = e.getX();
		mouseY = e.getY();
		System.out.println(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clickedButton = NOBUTTON;
		System.out.println(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public int getClickedButton() {
		return clickedButton;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void updateStatus() {
		clickedButton = NOBUTTON;
	}
}
