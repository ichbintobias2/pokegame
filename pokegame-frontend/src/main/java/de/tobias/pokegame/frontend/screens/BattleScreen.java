package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.entities.EnemyMonster;
import de.tobias.pokegame.frontend.entities.HealthBar;
import de.tobias.pokegame.frontend.entities.PlayerMonster;
import de.tobias.pokegame.frontend.menu.AttackMenu;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class BattleScreen extends GameScreen {

	public BattleScreen() {
		super("BATTLE");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(new Dialog());
		
		// TODO these are placeholders
		PlayerMonster mon1 = PlayerMonster.instance();
		EnemyMonster mon2 = EnemyMonster.instance();
		
		mon1.setSpritesheetName("placeholder1");
		mon2.setSpritesheetName("placeholder2");
		
		this.getComponents().add(new HealthBar(800, 450, mon1));
		this.getComponents().add(new HealthBar(20, 20, mon2));
		this.getComponents().add(AttackMenu.instance());
		this.getComponents().add(BattleMenu.instance());
	}
}
