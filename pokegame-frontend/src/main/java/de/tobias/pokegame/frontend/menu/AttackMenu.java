package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.entities.EnemyMonster;
import de.tobias.pokegame.frontend.entities.PlayerMonster;

public class AttackMenu extends KeyboardMenu {
	private static final double x = Game.window().getResolution().getWidth() / 1.4;
	private static final double y = Game.window().getResolution().getHeight() / 3;
	private static final double width = 400;
	private static final double height = 500;
	
	private static AttackMenu instance;
	
	private boolean once = false;

	private static PlayerMonster mon1 = PlayerMonster.instance();
	private static EnemyMonster mon = EnemyMonster.instance();
	
	private AttackMenu() {
		super(x, y, width, height, mon1.getAttack(0).getName(), mon1.getAttack(1).getName(),
				mon1.getAttack(2).getName(), mon1.getAttack(3).getName(), "Back");
		
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
		if (instance == null) {
			instance = new AttackMenu();
		}
		
		return instance;
	}
	
	private void performAttack1() {
		int damage = 50; // TODO calculate damage
		
		if (mon.getCurrentHp() < damage) {
			mon.setCurrentHp(0);
			
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
