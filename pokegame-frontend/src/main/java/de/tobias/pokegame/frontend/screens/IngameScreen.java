package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import de.tobias.pokegame.frontend.ui.Dialog;
import de.tobias.pokegame.frontend.ui.InteractButton;
import de.tobias.pokegame.frontend.ui.LocationSign;

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
