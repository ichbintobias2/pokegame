package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.enums.SoundControl;

public class PauseMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 1.30;
	private static final double y = Game.window().getResolution().getHeight() * 0.1;
	private static final double width = 400;
	private static final double height = 400;
	
	private static PauseMenu instance;
	
	private static final String continueText = GameLogic.localize("pause.Continue");
	private static final String teamText = GameLogic.localize("pause.Team");
	private static final String playerText = GameLogic.localize("pause.Player");
	private static final String optionsText = GameLogic.localize("pause.Options");
	private static final String exitText = GameLogic.localize("pause.Exit");
	
	private boolean once = false;

	private PauseMenu() {
		super(x, y, width, height, continueText, teamText, playerText, optionsText, exitText);
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				GameLogic.setState(GameState.INGAME);
				instance.setVisible(false);
				break;
			case 1:
				showTeamInfo();
				break;
			case 2:
				showPlayerInfo();
				break;
			case 3:
				showOptionsMenu();
				break;
			case 4:
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

	private void showTeamInfo() {
		
	}
	
	private void showPlayerInfo() {
		
	}
	
	private void showOptionsMenu() {
		Game.screens().display("OPTIONS");
		Game.world().loadEnvironment("options");
		Game.world().camera().setClampToMap(true);
		GameLogic.setState(GameState.OPTIONS);
	}
}
