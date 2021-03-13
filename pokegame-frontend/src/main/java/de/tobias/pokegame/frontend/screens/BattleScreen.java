package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.entities.EnemyMonster;
import de.tobias.pokegame.frontend.entities.HealthBar;
import de.tobias.pokegame.frontend.entities.PlayerMonster;
import de.tobias.pokegame.frontend.entities.Type;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class BattleScreen extends GameScreen {
	
	public BattleScreen() {
		super("BATTLE");
	}

	@Override
	protected void initializeComponents() {
		this.getComponents().add(Dialog.instance());
		
		PlayerMonster.instance().setSpritesheetName("placeholder1");
		EnemyMonster.instance().setSpritesheetName("placeholder2");
		
		this.getComponents().add(new HealthBar(800, 450, true));
		this.getComponents().add(new HealthBar(20, 20, false));
		this.getComponents().add(new Type("Water", 810, 470));
		this.getComponents().add(BattleMenu.instance());
	}
	
	public static void addToScreen(GuiComponent e) {
		Game.screens().get("BATTLE").getComponents().add(e);
	}
	
	public static void removeFromScreen(GuiComponent e) {
		Game.screens().get("BATTLE").getComponents().remove(e);
	}
}
