package de.tobias.pokegame.frontend.entities;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.tobias.pokegame.frontend.entities.enums.PlayerState;

@EntityInfo(width = 16, height = 16)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 16, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable {
	private static Player instance;
	private PlayerState state;

	private Player() {
		super("src\\main\\resources\\sprites\\player");

		this.addController(createMovementController());
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

	@Override
	protected IMovementController createMovementController() {
		return new KeyboardEntityController<>(this);
	}
	
	public PlayerState getState() {
		return state;
	}
}
