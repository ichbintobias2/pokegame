package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.Monster;

public class StatCalc {
	private Monster monster;
	private int damageTaken = 0;
	
	public StatCalc(Monster monster) {
		this.monster = monster;
	}
	
	public int getMaxHp() {
		return 250; // TODO
	}
	
	public int getCurrentHp() {
		return getMaxHp() - damageTaken;
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
	
	public void receiveDamage(int damage) {
		damageTaken += damage;
	}
}
