package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.pokegame.backend.calc.StatCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import lombok.Getter;

public abstract class Monster extends Creature {
	@Getter protected List<String> attacks = new ArrayList<String>();
	@Getter protected List<String> types = new ArrayList<String>();
	@Getter protected String name = new String();
	
	private StatCalc calc;
	private CurrentMonster db;
	
	protected Monster(String monsterName) {
		this.db = NitriteManager.getCurrentMonsterByName(monsterName);
		this.calc = new StatCalc(db);
	}
	
	public String getAttack(int slot) {
		// return attacks.get(slot);
		return "";
	}

	public StatCalc getData() {
		return calc;
	}
	
	public void setCurrentHp(int currentHp) {
		// TODO
	}
	
	public void receiveDamage(int damage) {
		// TODO
	}
}
