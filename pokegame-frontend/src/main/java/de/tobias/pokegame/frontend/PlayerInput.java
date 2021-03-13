package de.tobias.pokegame.frontend;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.input.Input;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.menu.InteractButton;
import de.tobias.pokegame.frontend.screens.Dialog;

public final class PlayerInput {

	public static void init() {
		Input.keyboard().onKeyReleased(KeyEvent.VK_F, e -> Player.instance().talkToNPC());
		
		Input.keyboard().onKeyReleased(KeyEvent.VK_E, e -> InteractButton.instance().interact());
		
		Input.keyboard().onKeyReleased(KeyEvent.VK_ESCAPE, e -> GameLogic.showPauseMenu());
		
		Input.keyboard().onKeyReleased(KeyEvent.VK_ENTER, e -> Dialog.instance().nextLine());
	}
}
