package de.tobias.pokegame.frontend;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.input.Input;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import de.tobias.pokegame.frontend.ui.Dialog;
import de.tobias.pokegame.frontend.ui.InteractButton;

public final class PlayerInput {
	
	public static void init() {
		Input.keyboard().onKeyReleased(KeyEvent.VK_F, e -> Player.instance().talkToNPC());
		Input.keyboard().onKeyReleased(KeyEvent.VK_E, e -> InteractButton.instance().interact());
		Input.keyboard().onKeyReleased(KeyEvent.VK_ESCAPE, e -> handleEscapeKey());
		Input.keyboard().onKeyReleased(KeyEvent.VK_ENTER, e -> Dialog.instance().nextLine());
	}
	
	private static void handleEscapeKey() {
		if (GameLogic.getState() == GameState.BOX || GameLogic.getState() == GameState.REGISTRY) {
			Game.screens().display("INGAME");
			GameLogic.setState(GameState.INGAME);
			PauseMenu.instance().update();
		} else {
			 GameLogic.showPauseMenu();
		}
	}
}
