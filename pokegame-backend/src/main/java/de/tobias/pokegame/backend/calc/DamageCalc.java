package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.Attack;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class DamageCalc {
	
	private int playerAtk;
	private int enemyDef;
	private int attackerLevel;
	
	public DamageCalc(int playerAtk, int enemyDef, int attackerLevel) {
		this.playerAtk = playerAtk;
		this.enemyDef = enemyDef;
		this.attackerLevel = attackerLevel;
	}
	
	public int calculateDamage(String attackName) {
		Attack attack = NitriteManager.getAttackByName(attackName);
		int baseAtkDmg = attack.getBaseDamage();
		
		double baseDamage = Math.floor(Math.floor(Math.floor(2 * attackerLevel / 5 + 2) * baseAtkDmg * playerAtk / enemyDef) / 50) + 2;
		
		return (int) baseDamage;
	}
}
