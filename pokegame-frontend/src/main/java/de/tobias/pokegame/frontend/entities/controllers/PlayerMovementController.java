package de.tobias.pokegame.frontend.entities.controllers;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.entities.Trigger;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.pokegame.backend.wild.EncounterCheck;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.entities.Player;

public class PlayerMovementController extends KeyboardEntityController<IMobileEntity> {
	
	private final List<Integer> up = Arrays.asList(KeyEvent.VK_W);
	private final List<Integer> down = Arrays.asList(KeyEvent.VK_S);
	private final List<Integer> left = Arrays.asList(KeyEvent.VK_A);
	private final List<Integer> right = Arrays.asList(KeyEvent.VK_D);
	
	private boolean canEncounter = true;
	
	private int movementCooldown = 100;
	private long lastMovement = 0;
	
	public PlayerMovementController(IMobileEntity entity) {
		super(entity);
		
		this.onMovementCheck(e -> {
			checkTallGrass();
			return GameLogic.getState() == GameState.INGAME;
		});
	}
	
	/* TODO this method is commented out because it is not working as intended yet.
	@Override
	public void handlePressedKey(KeyEvent e) {
		float tileSize = 16 * Game.world().camera().getRenderScale(); // Player should move exactly one tile per pressed key
		
		if (Game.time().since(lastMovement) > movementCooldown) {
			if (this.up.contains(e.getKeyCode())) {
				this.setDy(this.getDy() - tileSize);
			} else if (this.down.contains(e.getKeyCode())) {
				this.setDy(this.getDy() + tileSize);
			} else if (this.left.contains(e.getKeyCode())) {
				this.setDx(this.getDx() - tileSize);
			} else if (this.right.contains(e.getKeyCode())) {
				this.setDx(this.getDx() + tileSize);
			}
			
			lastMovement = Game.time().now();
		}
	} */
	
	private void checkTallGrass() {
		for (IEntity entity : Game.world().environment().findEntities(GeometricUtilities.extrude(Player.instance().getBoundingBox(), 2))) {
			if (entity instanceof Trigger && entity.getProperties().hasCustomProperty("triggerType")) {
				if (canEncounter && entity.getProperties().getStringValue("triggerType").equals("grass")) {
					Integer id = EncounterCheck.getEncounter("route1"); // TODO get name dynamically
					
					if (id != null) {
						// Start a battle and set canEncounter to false to not cause a loop where infinite battles are started
						BattleControl.startWildBattle(id);
						canEncounter = false;
					}
				}
			}
		}
	}
}
