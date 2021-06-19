package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.Entity;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.entities.controllers.PlayerMovementController;

@EntityInfo(width = 16, height = 16)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 16, collisionBoxHeight = 16, collision = true)
public class Player extends Creature {
	
	private static Player instance;
	
	private Player() {
		super("npc_placeholder");
		
		this.setController(IMovementController.class, new PlayerMovementController(this));
	}
	
	public static Player instance() {
		if (instance == null) {
			instance = new Player();
		}
		
		return instance;
	}
	
	public void talkToNPC() {
		if (GameLogic.getState() != GameState.INGAME) {
			return;
		}
		
		for (IEntity entity : Game.world().environment()
				.findEntities(GeometricUtilities.extrude(Player.instance().getBoundingBox(), 2))) {
			if (entity instanceof Entity) {
				entity.sendMessage(Player.instance(), Entity.ANY_MESSAGE);
			}
		}
	}
}
