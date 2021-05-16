package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class BattleScreen extends GameScreen {
	
	public BattleScreen() {
		super("BATTLE");
	}
	
	@Override
	protected void initializeComponents() {
		this.getComponents().add(Dialog.instance());
		this.getComponents().add(BattleMenu.instance());
	}
	
	public static void addToScreen(GuiComponent e) {
		Game.screens().get("BATTLE").getComponents().add(e);
	}
	
	public static void removeFromScreen(GuiComponent e) {
		Game.screens().get("BATTLE").getComponents().remove(e);
	}
}
