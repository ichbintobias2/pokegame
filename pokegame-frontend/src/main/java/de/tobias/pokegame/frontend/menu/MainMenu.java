package de.tobias.pokegame.frontend.menu;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;

public class MainMenu extends KeyboardMenu {
	// private static final String CONFIRM = "";
	
	private static final double centerX = Game.window().getResolution().getWidth() / 2.0;
	private static final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
	private static final double buttonWidth = 450;

	public MainMenu() {
		super(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2, "Play",
				"Instructions", "Exit");

		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				startGame();
				break;
			case 1:
				showInstructions();
				break;
			case 2:
				System.exit(0);
			}
		});
	}
	
	private void startGame() {
		setEnabled(false);
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.screens().display("INGAME-SCREEN");
			Game.world().loadEnvironment(GameLogic.START_LEVEL);
			GameLogic.setState(GameState.INGAME);
		});
	}
	
	private void showInstructions() {
		// setEnabled(false);
		// TODO
	}
}
