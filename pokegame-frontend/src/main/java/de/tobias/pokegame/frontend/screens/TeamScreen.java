package de.tobias.pokegame.frontend.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.Savegame;
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.entities.Monster;

public class TeamScreen extends GameScreen {
	
	private BufferedImage background = Resources.images().get("src/main/resources/sprites/team/teamview1.png");
	private BufferedImage module1 = Resources.images().get("src/main/resources/sprites/team/module1.png");
	
	private double scaleFactor = 4.5;
	
	List<Integer> yCoordinates = Arrays.asList(4);
	
	public TeamScreen() {
		super("TEAM");
	}
	
	@Override
	protected void initializeComponents() {
		// this.getComponents().add(Dialog.instance());
	}
	
	@Override
	public void render(Graphics2D g) {
		double imageOffset = (Game.window().getWidth() - (background.getWidth() * scaleFactor)) / 2;
		
		ImageRenderer.renderScaled(g, background, imageOffset, 0, scaleFactor);
		
		List<CurrentMonster> team = Savegame.getPlayerTeam();
		for (int i=0; i<team.size(); i++) {
			CurrentMonster mon = team.get(i);
			Monster uiMon = new Monster(0, 0, mon);
			
			// coordinates for the module placement
			double x = (scaleFactor * 148) + imageOffset;
			double y = yCoordinates.get(i) * scaleFactor;
			
			// render module image
			ImageRenderer.renderScaled(g, module1, x, y, scaleFactor);
			
			// render monster name and level
			g.setFont(Fonts.PIXEL_EMULATOR.deriveFont(30f));
			g.drawString(uiMon.getData().getName(), (int) (x + (43 * scaleFactor)), (int) (y + (10 * scaleFactor)));
			g.drawString("Lv. "+ uiMon.getData().getLevel(), (int) (x + (120 * scaleFactor)), (int) (y + (10 * scaleFactor)));
			
			// maximum width and current width of the health bar
			double w = 103  * scaleFactor;
			double percentHp = uiMon.getStats().getCurrentHp() / uiMon.getStats().getMaxHp();
			
			// render health bar
			g.setColor(Color.GREEN);
			g.fillRect((int) (x + 58 * scaleFactor), (int) (y + 24 * scaleFactor), (int) (w * percentHp), (int) (7 * scaleFactor));
		}
	}
}
