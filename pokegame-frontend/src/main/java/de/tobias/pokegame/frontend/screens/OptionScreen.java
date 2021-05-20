package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.menu.OptionMenu;

public class OptionScreen extends GameScreen {
	
	public OptionScreen() {
		super("OPTIONS");
	}
	
	@Override
	protected void initializeComponents() {
		this.getComponents().add(new OptionMenu());
	}
}
