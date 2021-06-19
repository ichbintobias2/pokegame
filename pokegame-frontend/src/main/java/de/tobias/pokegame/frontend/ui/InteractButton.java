package de.tobias.pokegame.frontend.ui;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.Trigger;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.screens.LocationTriggers;

public class InteractButton extends GuiComponent {
	
	private static InteractButton instance;
	
	private InteractButton() {
		super(0, 0);
	}
	
	public static InteractButton instance() {
		if (instance == null) {
			instance = new InteractButton();
		}

		return instance;
	}
	
	@Override
	public void render(Graphics2D g) {
		if (GameLogic.getState() == GameState.INGAME && canTrigger()) {
			// BufferedImage useButton;

			// final Point2D loc = Game.world().camera().getViewportLocation(Player.instance().getCenter());
		    // ImageRenderer.render(g, useButton, (loc.getX() * Game.world().camera().getRenderScale() - useButton.getWidth() / 2.0), loc.getY() * Game.world().camera().getRenderScale() - (useButton.getHeight() * 2.5));
		}
	}
	
	public void interact() {
		if (GameLogic.getState() == GameState.INGAME && canTrigger()) {
			LocationTriggers.changeLocation(getSurroundingTriggerProperty());
		}
	}
	
	private boolean canTrigger() {
		for (IEntity entity : Game.world().environment().findEntities(GeometricUtilities.extrude(Player.instance().getBoundingBox(), 2))) {
			if (entity instanceof Trigger) {
				return true;
			}
		}
		
		return false;
	}
	
	private String getSurroundingTriggerProperty() {
		for (IEntity entity : Game.world().environment().findEntities(GeometricUtilities.extrude(Player.instance().getBoundingBox(), 2))) {
			if (entity instanceof Trigger) {
				return entity.getProperties().getStringValue("whereto");
			}
		}
		
		return null;
	}
}
