package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.BaseMonster;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.entities.monster.Nature;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class StatCalc {
	
	private BaseMonster bMonster;
	private CurrentMonster cMonster;
	private int damageTaken = 0;
	
	private Nature natureObject;
	
	public StatCalc(CurrentMonster cm) {
		this.cMonster = cm;
		this.bMonster = NitriteManager.getBaseMonsterByRegistryNr(cm.getRegistryNumber());
		natureObject = NitriteManager.getNatureByName(cm.getNature());
	}
	
	public int getMaxHp() {
		int b = bMonster.getBaseHp();
		int i = cMonster.getDvHp();
		int e = cMonster.getEvHp();
		int l = cMonster.getLevel();
		
		double value = Math.floor((2 * b + i + e) * l / 100 + l + 10);
		
		return (int) value;
	}
	
	public int getCurrentHp() {
		return getMaxHp() - damageTaken;
	}
	
	public int getCurrentAtk() {
		double nature = natureObject.getAttack();
		return calculateFromBase(bMonster.getBaseAttack(), cMonster.getDvAttack(), cMonster.getEvAttack(), cMonster.getLevel(), nature);
	}
	
	public int getCurrentDef() {
		double nature = natureObject.getDefense();
		return calculateFromBase(bMonster.getBaseDefense(), cMonster.getDvDefense(), cMonster.getEvDefense(), cMonster.getLevel(), nature);
	}
	
	public int getCurrentSpAtk() {
		double nature = natureObject.getSpAtk();
		return calculateFromBase(bMonster.getBaseSpAtk(), cMonster.getDvSpAtk(), cMonster.getEvSpAtk(), cMonster.getLevel(), nature);
	}
	
	public int getCurrentSpDef() {
		double nature = natureObject.getSpDef();
		return calculateFromBase(bMonster.getBaseSpDef(), cMonster.getDvSpDef(), cMonster.getEvSpDef(), cMonster.getLevel(), nature);
	}
	
	public int getCurrentSpeed() {
		double nature = natureObject.getSpeed();
		return calculateFromBase(bMonster.getBaseSpeed(), cMonster.getDvSpeed(), cMonster.getEvSpeed(), cMonster.getLevel(), nature);
	}	
	
	public void receiveDamage(double damage) {
		damageTaken += (int) damage;
	}
	
	private int calculateFromBase(int base, int dv, int ev, int level, double nature) {
		double value = Math.floor(Math.floor((2 * base + dv + ev) * level / 100 + 5) * nature);
		
		return (int) value;
	}
}
