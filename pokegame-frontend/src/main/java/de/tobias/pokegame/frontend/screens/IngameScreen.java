package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.menu.InteractButton;
import de.tobias.pokegame.frontend.menu.LocationSign;
import de.tobias.pokegame.frontend.menu.PauseMenu;

public class IngameScreen extends GameScreen {

	public IngameScreen() {
		super("INGAME");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(PauseMenu.instance());
		this.getComponents().add(Dialog.instance());
		this.getComponents().add(InteractButton.instance());
		this.getComponents().add(LocationSign.instance());
	}
}
