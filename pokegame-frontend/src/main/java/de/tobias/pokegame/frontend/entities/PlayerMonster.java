package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

@EntityInfo(width = 16, height = 16)
//@AnimationInfo(spritePrefix = "placeholder1")
public class PlayerMonster extends Monster {
	private static PlayerMonster instance;
	
	private PlayerMonster(CurrentMonster cm) {
		super(cm);
	}
	
	private PlayerMonster() {
		super();
		// Empty constructor to use for initialization
	}
	
	public static PlayerMonster instance() {
		if (instance == null) {
			instance = new PlayerMonster();
		}
		
		return instance;
	}
	
	public void set(CurrentMonster cm) {
		instance = new PlayerMonster(cm);
	}
}
