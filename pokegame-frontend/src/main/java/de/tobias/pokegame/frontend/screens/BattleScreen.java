package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.entities.HealthBar;
import de.tobias.pokegame.frontend.entities.Monster;
import de.tobias.pokegame.frontend.menu.AttackMenu;

public class BattleScreen extends GameScreen {

	public BattleScreen() {
		super("BATTLE");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(new Dialog());
		
		// TODO these are placeholders
		Monster mon1 = new Monster();
		Monster mon2 = new Monster();
		mon1.setSpritesheetName("placeholder1");
		mon2.setSpritesheetName("placeholder2");
		
		this.getComponents().add(new HealthBar(800, 450, mon1));
		this.getComponents().add(new HealthBar(20, 20, mon2));
		this.getComponents().add(AttackMenu.createInstance(mon1, mon2));
	}
}
