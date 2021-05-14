package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.entities.Monster;
import de.tobias.pokegame.frontend.screens.BattleScreen;

public class AttackMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 1.4;
	private static final double y = Game.window().getResolution().getHeight() / 3;
	private static final double width = 400;
	private static final double height = 500;
	
	private static AttackMenu instance;
	
	private boolean once = false;
	
	private AttackMenu() {
		super(x, y, width, height);
	}
	
	private AttackMenu(Monster mon) {
		super(x, y, width, height, mon.getAttack(0), mon.getAttack(1),
				mon.getAttack(2), mon.getAttack(3), "Back");
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				performAttack(0);
				break;
			case 1:
				performAttack(1);
				break;
			case 2:
				performAttack(2);
				break;
			case 3:
				performAttack(3);
				break;
			case 4:
				backToBattleMenu();
				break;
			}
		});
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		
		if (!once) {
			instance.setVisible(false);
			instance.setEnabled(false);
			once = true;
		}
	}
	
	public static AttackMenu instance() {
		if (instance == null) {
			instance = new AttackMenu();
		}
		
		return instance;
	}
	
	public void set(Monster mon) {
		BattleScreen.removeFromScreen(AttackMenu.instance());
		instance = new AttackMenu(mon);
		BattleScreen.addToScreen(AttackMenu.instance());
	}
	
	private void performAttack(int i) {
		BattleControl.performPlayerAttack(i);
	}
	
	private void backToBattleMenu() {
		instance.setVisible(false);
		instance.setEnabled(false);
		BattleMenu.instance().setVisible(true);
		BattleMenu.instance().setEnabled(true);
	}
}
