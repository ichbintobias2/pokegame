package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.backend.calc.StatCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.frontend.constants.Animations;

@EntityInfo(width = 64, height = 64)
public class Monster extends Creature {
	
	private CurrentMonster cm;
	private StatCalc stats;
	
	public Monster(double x, double y, CurrentMonster cm) {
		super();
		
		this.setX(x);
		this.setY(y);
		this.cm = cm;
		this.stats = new StatCalc(cm);
		
		String animationName = cm.getRegistryNumber() + Animations.BASE_PLAYER;
		this.setSpritesheetName("player_battle");
		
		this.createAnimationController();
		this.animations().setDefault(new Animation(Resources.spritesheets().get(animationName), true));
		this.animations().play(animationName);
	}
	
	public CurrentMonster getData() {
		return cm;
	}
	
	public StatCalc getStats() {
		return stats;
	}
	
	public String getAttack(int slot) {
		return cm.getAttacks().get(slot);
	}
}
