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
		double hp1 = monster.getBaseHp() + monster.getDvHp() + (Math.sqrt(monster.getEvHp() / 4)) * monster.getLevel();
		double hp2 = (hp1 + monster.getLevel() + 10) / 100;
		
		return (int) hp2;
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
		int res1 = 2* base + dv + (ev / 4) * level;
		int res2 = (res1 + 5) / 100;
		
		return (int) (res2 * nature);
	}
}
