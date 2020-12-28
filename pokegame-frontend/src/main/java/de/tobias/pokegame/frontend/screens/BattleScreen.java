package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;

public class BattleScreen extends GameScreen {

	public BattleScreen() {
		super("BATTLE");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(new Dialog());
	}

}
