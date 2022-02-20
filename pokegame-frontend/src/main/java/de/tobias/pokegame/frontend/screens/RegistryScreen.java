package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.backend.entities.player.Registry;
import de.tobias.pokegame.backend.entities.player.RegistryEntry;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.frontend.constants.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class RegistryScreen extends GameScreen {
	
	private final double scaleFactor = Images.SCALE_FACTOR;
	
	private final BufferedImage background = Images.REGISTRY_BG1;
	
	private final List<Integer> xCoordinates = Arrays.asList(6, 43, 80, 117, 154, 191, 6, 43, 80, 117, 154, 191);
	private final List<Integer> yCoordinates = Arrays.asList(26, 26, 26, 26, 26, 26, 63, 63, 63, 63, 63, 63);
	
	public RegistryScreen() {
		super("REGISTRY");
	}
	
	@Override
	protected void initializeComponents() {
		//
	}
	
	@Override
	public void render(final Graphics2D g) {
		ImageRenderer.renderScaled(g, background, 0, 0, scaleFactor);
		Registry registry = NitriteManager.getRegistry();
		for (int i=0; i<registry.getEntries().size(); i++) {
			RegistryEntry entry = registry.getEntries().get(i);
			BufferedImage monsterIcon = Images.getMonsterSprite(entry.getMonsterId());
			ImageRenderer.renderScaled(g, monsterIcon, xCoordinates.get(i) * scaleFactor, yCoordinates.get(i) * scaleFactor, scaleFactor);
		}
	}
}
