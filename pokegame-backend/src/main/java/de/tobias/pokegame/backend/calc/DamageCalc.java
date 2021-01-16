package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.Attack;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class DamageCalc {
	private Attack playerAtk;
	private int enemyDef;

	public DamageCalc(String playerAtk, int enemyDef) {
		this.playerAtk = getAttackInfoFromString(playerAtk);
		this.enemyDef = enemyDef;
	}
	
	public int calculateDamage() {
		return 0;
	}
	
	private Attack getAttackInfoFromString(String attackName) {
		return NitriteManager.getAttackByName(attackName);
	}
}
