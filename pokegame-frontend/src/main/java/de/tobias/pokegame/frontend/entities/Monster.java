package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.pokegame.backend.calc.StatCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import lombok.Getter;

public abstract class Monster extends Creature {
	@Getter private CurrentMonster cm;
	@Getter private StatCalc stats;
	
	protected Monster(CurrentMonster cm) {
		this.cm = cm;
		this.stats = new StatCalc(cm);
	}
	
	protected Monster() {
		// for initialization
	}
	
	public String getAttack(int slot) {
		return cm.getAttacks().get(slot);
	}
}
