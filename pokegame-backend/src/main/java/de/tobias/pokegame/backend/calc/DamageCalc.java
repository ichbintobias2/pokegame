package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.Attack;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class DamageCalc {
	private int playerAtk;
	private int enemyDef;

	public DamageCalc(int playerAtk, int enemyDef) {
		this.playerAtk = playerAtk;
		this.enemyDef = enemyDef;
	}
	
	public int calculateDamage(String attackName) {
		Attack attack = getAttackInfoFromString(attackName);
		int baseAtkDmg = attack.getBaseDamage();
		
		// TODO implement actual calculation
		
		return 50;
	}
	
	private Attack getAttackInfoFromString(String attackName) {
		return NitriteManager.getAttackByName(attackName);
	}
}
