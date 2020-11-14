package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.entities.enums.GameState;

public class IngameScreen extends GameScreen {

	public static final String NAME = "INGAME-SCREEN";
	public static KeyboardMenu pauseMenu;

	public IngameScreen() {
		super(NAME);
	}

	@Override
	protected void initializeComponents() {
		final double centerX = Game.window().getResolution().getWidth() / 2.0;
		final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
		final double buttonWidth = 450;

		pauseMenu = new KeyboardMenu(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2,
				"Continue", "Exit");
		
		pauseMenu.onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				GameLogic.setState(GameState.INGAME);
				break;
			case 1:
				System.exit(0);
				break;
			default:
				break;
			}
		});
		
		this.getComponents().add(pauseMenu);
	}
}
