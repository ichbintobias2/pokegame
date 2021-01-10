package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.EntityInfo;

@EntityInfo(width = 16, height = 16)
//@AnimationInfo(spritePrefix = "placeholder1")
public class EnemyMonster extends Monster {
	private static EnemyMonster instance;
	
	private EnemyMonster() {
		super(2L);
	}
	
	public static EnemyMonster instance() {
		if (instance == null) {
			instance = new EnemyMonster();
		}

		return instance;
	}
}
