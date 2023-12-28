package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.entities.Monster;
import de.tobias.pokegame.frontend.entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class TeamScreen extends ScreenWithControls {
	
	private final BufferedImage background = Images.TEAM_BG_1;
	private final BufferedImage module1 = Images.TEAM_MODULE_1;
	private final BufferedImage module1Selected = Images.TEAM_MODULE_1_SELECTED; // TODO
	
	private final double scaleFactor = Images.SCALE_FACTOR;
	
	List<Integer> yCoordinates = Arrays.asList(4, 59, 114, 169);
	
	public TeamScreen() {
		super("TEAM");
		
		int currentPlayerTeamSize = Player.instance().team().list().size();
		handleUpKeyPressed(currentPlayerTeamSize-1, () -> {
			Game.log().info(String.valueOf(currentIndex));
		});
		
		handleDownKeyPressed(currentPlayerTeamSize-1, () -> {
			Game.log().info(String.valueOf(currentIndex));
		});
		
		handleKeyPressed(KeyEvent.VK_ENTER, () -> {
			// TODO open new menu/dialog here
			CurrentMonster selectedMon = Player.instance().team().list().get(currentIndex);
			Game.log().info("Selected " + selectedMon.getName());
		});
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
			BufferedImage moduleImage = i == currentIndex ? module1Selected : module1;
			ImageRenderer.renderScaled(g, moduleImage, x, y, scaleFactor);
			ImageRenderer.renderScaled(g, Images.getMonsterSprite(mon.getRegistryNumber()), 153 * scaleFactor,9 * scaleFactor, scaleFactor);
			
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
