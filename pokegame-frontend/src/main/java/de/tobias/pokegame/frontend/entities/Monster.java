package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.backend.calc.StatCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.frontend.constants.Animations;

public class Monster {
	
	private final CurrentMonster cm;
	private final StatCalc stats;
	
	public Monster(CurrentMonster cm) {
		super();
		this.cm = cm;
		this.stats = new StatCalc(cm);
	}
	
	public CurrentMonster getData() {
		return cm;
	}
	
	public StatCalc getStats() {
		return stats;
	}
	
	public String getAttack(int slot) {
		return cm.getAttacks().get(slot);
	}
}
