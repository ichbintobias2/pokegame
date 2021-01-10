package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.pokegame.backend.calc.StatCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.PersistenceManager;
import lombok.Getter;

public abstract class Monster extends Creature {
	@Getter protected List<String> attacks = new ArrayList<String>();
	@Getter protected List<String> types = new ArrayList<String>();
	@Getter protected String name = new String();
	
	private StatCalc calc;
	private CurrentMonster db;
	
	protected Monster(String monsterName) {
		this.calc = new StatCalc(monsterName);
		this.db = PersistenceManager.get(CurrentMonster.class, 1L);
		
		attacks.add(db.getAttack1());
		attacks.add(db.getAttack2());
		attacks.add(db.getAttack3());
		attacks.add(db.getAttack4());
		types.add("");
	}
	
	public String getAttack(int slot) {
		return attacks.get(slot);
	}

	public int getMaxHp() {
		return calc.getMaxHp();
	}
	
	public int getCurrentHp() {
		return calc.getCurrentHp();
	}
	
	public int getCurrentAttack() {
		return calc.getCurrentAtk();
	}
	
	public int getCurrentDefense() {
		return calc.getCurrentDef();
	}
	
	public int getCurrentSpAtk() {
		return calc.getCurrentDef();
	}
	
	public int getCurrentSpDef() {
		return calc.getCurrentDef();
	}
	
	public int getCurrentSpeed() {
		return calc.getCurrentDef();
	}
	
	public void setCurrentHp(int currentHp) {
		// TODO
	}
	
	public void receiveDamage(int damage) {
		// TODO
	}
}
