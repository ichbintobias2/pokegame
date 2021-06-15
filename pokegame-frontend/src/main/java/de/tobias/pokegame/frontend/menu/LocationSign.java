package de.tobias.pokegame.frontend.menu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.constants.Images;

public class LocationSign extends GuiComponent {
	
	private static LocationSign instance;
	
	private BufferedImage signImage = Images.SIGN_1;
	private double scaleFactor = Game.window().getHeight() / 240;
	
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
			ImageRenderer.renderScaled(g, signImage, 0, 0, scaleFactor);
			g.setFont(Fonts.PIXEL_EMULATOR);
			g.setColor(Color.BLACK);
			TextRenderer.render(g, locationText, 30, 30, false);
		}
	}
	
	public void setVisibleWithText(String locationText) {
		instance.timestamp = Game.time().now();
		instance.locationText = locationText;
	}
}
