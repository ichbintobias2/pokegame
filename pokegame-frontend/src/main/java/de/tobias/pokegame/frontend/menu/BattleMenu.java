package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;

public class BattleMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 1.4;
	private static final double y = Game.window().getResolution().getHeight() / 3;
	private static final double width = 400;
	private static final double height = 400;
	
	private static BattleMenu instance;
	
	private boolean once = false;

	private BattleMenu() {
		super(x, y, width, height, "Attack", "Item", "Team", "Surrender");
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				showAttackMenu();
				break;
			case 1:
				showItemMenu();
				break;
			case 2:
				showTeamMenu();
				break;
			case 3:
				showSurrenderMenu();
				break;
			}	
		});
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		if (!once) {
			instance.setEnabled(false);
			once = true;
		}
	}
	
	public static BattleMenu instance() {
		if (instance == null) {
			instance = new BattleMenu();
		}

		return instance;
	}
	
	private void showAttackMenu() {
		AttackMenu.instance().setVisible(true);
		AttackMenu.instance().setEnabled(true);
		instance.setEnabled(false);
		instance.setVisible(false);
	}
	
	private void showItemMenu() {
		
	}
	
	private void showTeamMenu() {
		
	}
	
	private void showSurrenderMenu() {
		
	}
}
