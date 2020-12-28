package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

@EntityInfo(width = 16, height = 16)
// @AnimationInfo(spritePrefix = "placeholder1")
public class Monster extends Creature {
	public CurrentMonster data = new CurrentMonster();
	
	public CurrentMonster getData() {
		return data;
	}
	
	public int getMaxHp() {
		return 420;
	}
	
	public void receiveDamage(int damage) {
		data.setCurrentHp(data.getCurrentHp() - damage);
	}
}
