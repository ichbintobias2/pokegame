package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.EntityInfo;

@EntityInfo(width = 16, height = 16)
//@AnimationInfo(spritePrefix = "placeholder1")
public class PlayerMonster extends Monster {
	private static PlayerMonster instance;
	
	private PlayerMonster() {
		super("playerMonsterTest");
	}
	
	public static PlayerMonster instance() {
		if (instance == null) {
			instance = new PlayerMonster();
		}

		return instance;
	}
}
