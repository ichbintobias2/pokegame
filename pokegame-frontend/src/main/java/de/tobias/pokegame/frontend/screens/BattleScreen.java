package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.entities.HealthBar;

public class BattleScreen extends GameScreen {

	public BattleScreen() {
		super("BATTLE");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(new Dialog());
		this.getComponents().add(new HealthBar(20, 20, null));
		this.getComponents().add(new HealthBar(800, 450, null));
	}

}
