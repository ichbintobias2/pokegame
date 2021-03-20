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
import de.gurkenlabs.litiengine.entities.Trigger;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.pokegame.backend.wild.EncounterCheck;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;

@EntityInfo(width = 16, height = 16)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 16, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable {
	
	private static Player instance;
	
	private boolean cooldown = false;

	private Player() {
		super("src\\main\\resources\\sprites\\player");

		KeyboardEntityController<Player> movementController = new KeyboardEntityController<>(this);
	    movementController.addUpKey(KeyEvent.VK_UP);
	    movementController.addDownKey(KeyEvent.VK_DOWN);
	    movementController.addLeftKey(KeyEvent.VK_LEFT);
	    movementController.addRightKey(KeyEvent.VK_RIGHT);
	    
		this.setController(IMovementController.class, movementController);
		this.getController(IMovementController.class).onMovementCheck(e -> {
			checkTallGrass();
			return GameLogic.getState() == GameState.INGAME;
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
	
	private void checkTallGrass() {
		for (IEntity entity : Game.world().environment().findEntities(GeometricUtilities.extrude(Player.instance().getBoundingBox(), 2))) {
			if (entity instanceof Trigger && entity.getProperties().getBoolValue("grass")) {
				if (EncounterCheck.isEncountered() && !cooldown) {
					int id = EncounterCheck.getEncounter("route1"); // TODO
					BattleControl.startWildBattle(id);
					
					// The idea is that this method should only trigger one battle and then wait until it is concluded
					cooldown = true;
				}
			}
		}
	}
}
