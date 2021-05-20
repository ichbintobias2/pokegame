package de.tobias.pokegame.frontend.menu;

import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;

public class LocationSign extends GuiComponent {
	
	private static LocationSign instance;
	
	private long timestamp;
	private String locationText;
	
	private LocationSign() {
		super(0, 0);
	}
	
	public static LocationSign instance() {
		if (instance == null) {
			instance = new LocationSign();
		}
		
		return instance;
	}
	
	@Override
	public void render(Graphics2D g) {
		if (Game.time().since(timestamp) < timestamp +3000) {
			// TODO add image and render locationText on it
		}
	}
	
	public void setVisibleWithText(String locationText) {
		instance.timestamp = Game.time().now();
		instance.locationText = locationText;
	}
}
