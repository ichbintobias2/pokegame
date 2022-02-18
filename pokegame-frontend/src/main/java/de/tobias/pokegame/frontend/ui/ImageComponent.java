package de.tobias.pokegame.frontend.ui;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;

import java.awt.*;

public class ImageComponent extends GuiComponent {
	
	private final double x;
	private final double y;
	private final Image image;
	private final double scaleFactor;
	
	public ImageComponent(double x, double y, Image image, double scaleFactor) {
		super(x, y);
		this.image = image;
		this.x = x * Game.graphics().getBaseRenderScale();
		this.y = y * Game.graphics().getBaseRenderScale();
		this.scaleFactor = scaleFactor * Game.graphics().getBaseRenderScale();
	}
	
	@Override
	public void render(Graphics2D g) {
		ImageRenderer.renderScaled(g, image, x, y, scaleFactor);
	}
}
