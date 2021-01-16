package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class StatCalc {
	private CurrentMonster monster;
	
	public StatCalc(String monsterName) {
		this.monster = NitriteManager.getCurrentMonsterByName(monsterName);
	}
	
	public int getMaxHp() {
		return 0;
	}
	
	public int getCurrentHp() {
		return 0;
	}
	
	public int getCurrentAtk() {
		return 0;
	}
	
	public int getCurrentDef() {
		return 0;
	}
	
	public int getCurrentSpAtk() {
		return 0;
	}
	
	public int getCurrentSpDef() {
		return 0;
	}
	
	public int getCurrentSpeed() {
		return 0;
	}
}
