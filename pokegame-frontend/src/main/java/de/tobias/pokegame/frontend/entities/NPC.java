package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.EntityMessageEvent;
import de.gurkenlabs.litiengine.entities.EntityMessageListener;
import de.tobias.pokegame.frontend.entities.enums.PlayerState;

@EntityInfo(width = 16, height = 16)
@AnimationInfo(spritePrefix = "player")
public class NPC extends Creature {
	private boolean isTalking = false;
	
	public NPC() {
		this.onMessage(ANY_MESSAGE, new EntityMessageListener() {
			
			@Override
			public void messageReceived(EntityMessageEvent event) {
				if (!isTalking) {
					getTalkedTo();
				}
			}
		});
	}
	
	private void getTalkedTo() {
		Player.instance().setState(PlayerState.LOCKED);
		isTalking = true;
		
		// say stuff
		
		Player.instance().setState(PlayerState.CONTROLLABLE);
		isTalking = false;
	}
}
