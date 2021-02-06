package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.pokegame.backend.calc.StatCalc;
import de.tobias.pokegame.backend.entities.monster.DbMonster;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import lombok.Getter;

public abstract class Monster extends Creature {
	@Getter protected List<String> attacks = new ArrayList<String>();
	@Getter protected List<String> types = new ArrayList<String>();
	@Getter protected String name = new String();
	
	private DbMonster db;
	private StatCalc calc;
	
	protected Monster(String monsterName) {
		db = NitriteManager.getDbMonsterByName(monsterName);
		calc = new StatCalc(db);
		
		attacks = db.getAttacks();
		types = db.getTypes();
		name = db.getName();
	}
	
	public String getAttack(int slot) {
		return attacks.get(slot);
	}

	public StatCalc getData() {
		return calc;
	}
	
	public int getLevel() {
		return db.getLevel();
	}
}
