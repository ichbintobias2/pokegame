package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.menu.PauseMenu;

public class IngameScreen extends GameScreen {

	public IngameScreen() {
		super("INGAME-SCREEN");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(PauseMenu.instance());
		this.getComponents().add(new Dialog());
	}
}
