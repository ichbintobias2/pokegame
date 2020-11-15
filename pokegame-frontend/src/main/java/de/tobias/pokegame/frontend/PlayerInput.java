package de.tobias.pokegame.frontend;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.input.Input;
import de.tobias.pokegame.frontend.entities.Player;

public final class PlayerInput {

	public static void init() {
		Input.keyboard().onKeyReleased(KeyEvent.VK_F, e -> Player.instance().talkToNPC());
		
		Input.keyboard().onKeyReleased(KeyEvent.VK_ESCAPE, e -> GameLogic.showPauseMenu());
	}
}
