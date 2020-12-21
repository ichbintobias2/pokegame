package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.enums.SoundControl;

public class PauseMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 2.0;
	private static final double y = Game.window().getResolution().getHeight() * 1 / 2;
	private static final double width = 450;
	private static final double height = y / 2;
	
	private static PauseMenu instance;
	
	private static final String continueText = GameLogic.localize("pause.Continue");
	private static final String exitText = GameLogic.localize("pause.Exit");
	
	private boolean once = false;

	private PauseMenu() {
		super(x, y, width, height / 2, continueText, exitText);
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				GameLogic.setState(GameState.INGAME);
				instance.setVisible(false);
				break;
			case 1:
				System.exit(0);
				break;
			}
		});
	}
	
	public static PauseMenu instance() {
		if (instance == null) {
			instance = new PauseMenu();
		}

		return instance;
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		if (!once) {
			instance.setVisible(false);
			once = true;
		}
	}
	
	public void update() {
		if (GameLogic.getState() == GameState.PAUSED) {
			Game.audio().playSound(SoundControl.MenuOpen);
			instance.setVisible(true);
		} else if (GameLogic.getState() != GameState.PAUSED) {
			Game.audio().playSound(SoundControl.MenuClose);
			instance.setVisible(false);
		}
	}
}
