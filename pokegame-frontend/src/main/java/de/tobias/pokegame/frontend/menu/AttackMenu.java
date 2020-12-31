package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.entities.Monster;

public class AttackMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 1.4;
	private static final double y = Game.window().getResolution().getHeight() / 3;
	private static final double width = 400;
	private static final double height = 500;
	
	private static AttackMenu instance;
	
	private boolean once = false;
	
	private final Monster mon;

	private AttackMenu(Monster mon1, Monster mon2) {
		super(x, y, width, height, mon1.getData().getAttack1(), mon1.getData().getAttack2(),
				mon1.getData().getAttack3(), mon1.getData().getAttack4(), "Back");
		this.mon = mon2;
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				performAttack1();
				break;
			case 1:
				performAttack2();
				break;
			case 2:
				performAttack3();
				break;
			case 3:
				performAttack4();
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
		return instance;
	}
	
	public static AttackMenu createInstance(Monster mon1, Monster mon2) {
		if (instance == null) {
			instance = new AttackMenu(mon1, mon2);
		}

		return instance;
	}
	
	private void performAttack1() {
		int damage = 50; // TODO calculate damage
		
		if (mon.getData().getCurrentHp() < damage) {
			mon.getData().setCurrentHp(0);
			
			BattleControl.stopBattle();
		} else {
			mon.receiveDamage(damage);
		}
	}

	private void performAttack2() {

	}

	private void performAttack3() {

	}

	private void performAttack4() {

	}
	
	private void backToBattleMenu() {
		instance.setVisible(false);
		instance.setEnabled(false);
		BattleMenu.instance().setVisible(true);
		BattleMenu.instance().setEnabled(true);
	}
}
