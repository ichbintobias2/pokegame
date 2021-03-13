package de.tobias.pokegame.frontend.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;

public class Type extends GuiComponent {
	BufferedImage img;
	private double x;
	private double y;
	
	public Type(String typeName, int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		
		img = Resources.images().get(".\\src\\main\\resources\\sprites\\type_"+ typeName.toLowerCase() +".png");
	}
	
	@Override
	public void render(Graphics2D g) {
		ImageRenderer.render(g, img, x, y);
	}
}
