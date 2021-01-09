package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.entities.Creature;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import lombok.Getter;

public abstract class Monster extends Creature {
	@Getter protected CurrentMonster data = new CurrentMonster();
	@Getter protected List<Attack> attacks = new ArrayList<Attack>();
	@Getter protected List<String> types = new ArrayList<String>();
	@Getter protected String name = new String();
	
	public Attack getAttack(int slot) {
		return attacks.get(slot);
	}
	
	public int getMaxHp() {
		data.getBaseSpeed();
		data.getDvSpeed();
		data.getEvSpeed();
		
		// TODO create actual calculation
		return 420;
	}
	
	public int getCurrentHp() {
		data.getBaseHp();
		data.getDvHp();
		data.getEvHp();
		
		// TODO create actual calculation
		return 125;
	}
	
	public void setCurrentHp(int currentHp) {
		// TODO
	}
	
	public int getCurrentSpeed() {
		data.getBaseSpeed();
		data.getDvSpeed();
		data.getEvSpeed();
		
		// TODO create actual calculation
		return 125;
	}
	
	public void receiveDamage(int damage) {
		// TODO
	}
}
