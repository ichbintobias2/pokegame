package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

public class StatCalc {
	private CurrentMonster monster;
	
	public StatCalc(String monsterName) {
		monster = getMonsterInfoFromString(monsterName);
	}
	
	private CurrentMonster getMonsterInfoFromString(String monsterName) {
		return new CurrentMonster(); // TODO
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
