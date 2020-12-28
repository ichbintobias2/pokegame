package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;

public class OptionMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 20;
	private static final double y = Game.window().getResolution().getHeight() / 100;
	private static final double width = 400;
	private static final double height = 300;

	private static final String languageText = GameLogic.localize("options.Language");
	private static final String fullscreenText = GameLogic.localize("options.Fullscreen");
	private static final String backText = GameLogic.localize("options.Back");

	public OptionMenu() {
		super(x, y, width, height, languageText, fullscreenText, backText);
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				showLanguageSubmenu();
				break;
			case 1:
				showFullscreenSubmenu();
				break;
			case 2:
				backToPauseScreen();
				break;
			}	
		});
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		if (GameLogic.getState() == GameState.OPTIONS) {
			this.setVisible(true);
		} else return;
	}

	private void showLanguageSubmenu() {
		
	}
	
	private void showFullscreenSubmenu() {
		
	}
	
	private void backToPauseScreen() {
		Game.screens().display("INGAME");
		Game.world().loadEnvironment("level1");		// TODO this should not be hard coded
		Game.world().camera().setClampToMap(false);
		GameLogic.setState(GameState.PAUSED);
		PauseMenu.instance().update();
	}
}
