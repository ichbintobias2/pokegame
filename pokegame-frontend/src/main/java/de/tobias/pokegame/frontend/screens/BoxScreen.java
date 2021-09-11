package de.tobias.pokegame.frontend.screens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.entities.monster.MonsterBox;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.entities.Player;

public class BoxScreen extends GameScreen {
	
	private BufferedImage background = Images.BOX_BG_1;
	private double scaleFactor = Images.SCALE_FACTOR;
	
	// use these to specify where the team icons are rendered. scaleFactor is applied automatically
	private final List<Integer> xCoordsTeam = Arrays.asList(295, 2, 3, 4);
	private final List<Integer> yCoordsTeam = Arrays.asList(45, 2, 3, 4);
	
	// coordinates for box items
	private final List<Integer> xCoordsBox = Arrays.asList(12, 2, 3, 4);
	private final List<Integer> yCoordsBox = Arrays.asList(24, 2, 3, 4);
	
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
			double x = xCoordsTeam.get(i) * scaleFactor;
			double y = yCoordsTeam.get(i) * scaleFactor;
			
			// render monster icon
			int monsterId = team.get(i).getRegistryNumber();
			BufferedImage monsterIcon = Images.getMonsterIcon(monsterId);
			ImageRenderer.renderScaled(g, monsterIcon, x, y, scaleFactor);
		}
	}
	
	private void renderBoxContent(Graphics2D g) {
		MonsterBox box = NitriteManager.getMonsterBox(398717393222000L); // TODO id
		for (int i=0; i<box.getEntries().size(); i++) {
			long id = box.getEntries().get(i).getCurrentMonsterId();
			CurrentMonster monster = NitriteManager.getCurrentMonsterById(id);
			BufferedImage monsterIcon = Images.getMonsterIcon(monster.getRegistryNumber());
			int x = xCoordsBox.get(i);
			int y = yCoordsBox.get(i);
			ImageRenderer.renderScaled(g, monsterIcon, x, y, scaleFactor);
		}
	}
}
