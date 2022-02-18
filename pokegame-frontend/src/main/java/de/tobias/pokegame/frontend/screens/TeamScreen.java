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
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.entities.Monster;
import de.tobias.pokegame.frontend.entities.Player;

public class TeamScreen extends GameScreen {
	
	private final BufferedImage background = Images.TEAM_BG_1;
	private final BufferedImage module1 = Images.TEAM_MODULE_1;
	
	private final double scaleFactor = Images.SCALE_FACTOR;
	
	List<Integer> yCoordinates = Arrays.asList(4, 59, 114, 169);
	
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
		
		List<CurrentMonster> team = Player.instance().team().list();
		for (int i=0; i<team.size(); i++) {
			CurrentMonster mon = team.get(i);
			Monster uiMon = new Monster(mon);
			
			// coordinates for the module placement
			double x = (scaleFactor * 148) + imageOffset;
			double y = yCoordinates.get(i) * scaleFactor;
			
			// render module image and monster sprite
			ImageRenderer.renderScaled(g, module1, x, y, scaleFactor);
			ImageRenderer.renderScaled(g, Images.getMonsterSprite(mon.getRegistryNumber()), 0,0, 2);
			
			// render monster name and level
			g.setColor(Color.BLACK);
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
