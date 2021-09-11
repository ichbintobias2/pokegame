package de.tobias.pokegame.frontend.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.entities.Player;

public class BoxScreen extends GameScreen {
	
	private BufferedImage background = Images.BOX_BG_1;
	private double scaleFactor = Images.SCALE_FACTOR;
	
	// use these to specify where the team icons are rendered. scaleFactor is applied automatically
	private final List<Integer> xCoords = Arrays.asList(295, 2, 3, 4);
	private final List<Integer> yCoords = Arrays.asList(45, 2, 3, 4);
	
	public BoxScreen() {
		super("BOX");
	}
	
	@Override
	public void render(Graphics2D g) {
		ImageRenderer.renderScaled(g, background, 0, 0, scaleFactor);
		renderTeam(g);
		renderBoxContent(g);
	}
	
	private void renderTeam(Graphics2D g) {
		List<CurrentMonster> team = Player.instance().team().list();
		for (int i=0; i<team.size(); i++) {			
			// coordinates for the icon placement
			double x = xCoords.get(i) * scaleFactor;
			double y = yCoords.get(i) * scaleFactor;
			
			// render monster icon
			int monsterId = team.get(i).getRegistryNumber();
			BufferedImage monsterIcon = Images.getMonsterIcon(monsterId);
			ImageRenderer.renderScaled(g, monsterIcon, x, y, scaleFactor);
		}
	}
	
	private void renderBoxContent(Graphics2D g) {
		// TODO render the monster icons inside the box
		// get box contents from db -> get id -> get icon for id -> render
	}
}
