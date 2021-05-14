package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.entities.HealthBar;
import de.tobias.pokegame.frontend.entities.Type;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class BattleScreen extends GameScreen {
	
	public BattleScreen() {
		super("BATTLE");
	}
	
	@Override
	protected void initializeComponents() {
		this.getComponents().add(Dialog.instance());
		
		this.getComponents().add(new HealthBar(800, 450, BattleControl.getPlayerMonster()));
		this.getComponents().add(new HealthBar(20, 20, BattleControl.getEnemyMonster()));
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
