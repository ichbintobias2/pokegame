package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

@EntityInfo(width = 16, height = 16)
//@AnimationInfo(spritePrefix = "placeholder1")
public class EnemyMonster extends Monster {
	private static EnemyMonster instance;
	
	private EnemyMonster(CurrentMonster cm) {
		super(cm);
	}
	
	private EnemyMonster() {
		super();
		// Empty constructor to use for initialization
	}
	
	public static EnemyMonster instance() {
		if (instance == null) {
			instance = new EnemyMonster();
		}
		
		return instance;
	}
	
	public void set(CurrentMonster cm) {
		instance = new EnemyMonster(cm);
	}
}
