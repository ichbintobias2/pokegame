package de.tobias.pokegame.frontend;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.input.Input;

public final class PlayerInput {
	private PlayerInput() {
	}

	public static void init() {
		Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE, e -> System.exit(0));
	}
}
