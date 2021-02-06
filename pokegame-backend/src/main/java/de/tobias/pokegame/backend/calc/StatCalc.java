package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.DbMonster;
import de.tobias.pokegame.backend.entities.monster.Nature;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class StatCalc {
	private DbMonster monster;
	private int damageTaken = 0;
	
	Nature natureObject;
	
	public StatCalc(DbMonster monster) {
		this.monster = monster;
		natureObject = NitriteManager.getNatureByName(monster.getNature());
	}
	
	public int getMaxHp() {
		int b = monster.getBaseHp();
		int i = monster.getDvHp();
		int e = monster.getEvHp();
		int l = monster.getLevel();
		
		double value = Math.floor((2 * b + i + e) * l / 100 + l + 10);
		
		return (int) value;
	}
	
	public int getCurrentHp() {
		return getMaxHp() - damageTaken;
	}
	
	public int getCurrentAtk() {
		double nature = natureObject.getAttack();
		return calculateFromBase(monster.getBaseAttack(), monster.getDvAttack(), monster.getEvAttack(), monster.getLevel(), nature);
	}
	
	public int getCurrentDef() {
		double nature = natureObject.getDefense();
		return calculateFromBase(monster.getBaseDefense(), monster.getDvDefense(), monster.getEvDefense(), monster.getLevel(), nature);
	}
	
	public int getCurrentSpAtk() {
		double nature = natureObject.getSpAtk();
		return calculateFromBase(monster.getBaseSpAtk(), monster.getDvSpAtk(), monster.getEvSpAtk(), monster.getLevel(), nature);
	}
	
	public int getCurrentSpDef() {
		double nature = natureObject.getSpDef();
		return calculateFromBase(monster.getBaseSpDef(), monster.getDvSpDef(), monster.getEvSpDef(), monster.getLevel(), nature);
	}
	
	public int getCurrentSpeed() {
		double nature = natureObject.getSpeed();
		return calculateFromBase(monster.getBaseSpeed(), monster.getDvSpeed(), monster.getEvSpeed(), monster.getLevel(), nature);
	}	
	
	public void receiveDamage(int damage) {
		damageTaken += damage;
	}
	
	private int calculateFromBase(int base, int dv, int ev, int level, double nature) {
		double value = Math.floor(Math.floor((2 * base + dv + ev) * level / 100 + 5) * nature);
		
		return (int) value;
	}
}
