package de.tobias.pokegame.frontend.entities;

import java.awt.event.KeyEvent;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.Entity;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.pokegame.frontend.entities.enums.PlayerState;

@EntityInfo(width = 16, height = 16)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 16, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable {
	private static Player instance;
	private PlayerState state = PlayerState.CONTROLLABLE;

	private Player() {
		super("src\\main\\resources\\sprites\\player");

		KeyboardEntityController<Player> movementController = new KeyboardEntityController<>(this);
	    movementController.addUpKey(KeyEvent.VK_UP);
	    movementController.addDownKey(KeyEvent.VK_DOWN);
	    movementController.addLeftKey(KeyEvent.VK_LEFT);
	    movementController.addRightKey(KeyEvent.VK_RIGHT);
	    
		this.setController(IMovementController.class, movementController);
		this.getController(IMovementController.class).onMovementCheck(e -> {
		      return this.getState() == PlayerState.CONTROLLABLE;
		});
	}

	public static Player instance() {
		if (instance == null) {
			instance = new Player();
		}

		return instance;
	}

	@Override
	public void update() {

	}
	
	public void talkToNPC() {
		if (Player.instance().getState() != PlayerState.CONTROLLABLE) {
			return;
		}

		for (IEntity entity : Game.world().environment()
				.findEntities(GeometricUtilities.extrude(Player.instance().getBoundingBox(), 2))) {
			if (entity instanceof Entity) {
				setState(PlayerState.LOCKED);
				entity.sendMessage(Player.instance(), Entity.ANY_MESSAGE);
			}
		}
	}
	
	public PlayerState getState() {
		return state;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}
}
