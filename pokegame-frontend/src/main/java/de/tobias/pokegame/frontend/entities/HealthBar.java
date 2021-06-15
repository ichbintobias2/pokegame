package de.tobias.pokegame.frontend.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.constants.Images;
import lombok.Getter;
import lombok.Setter;

public class HealthBar extends GuiComponent {
	
	private int x;
	private int y;
	
	private BufferedImage healthBox = Images.HEALTH_BAR_1;
	private int scaleFactor = Game.window().getHeight() / 240;
	
	@Getter @Setter
	private Monster monster;
	
	public HealthBar(int x, int y, Monster monster) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.monster = monster;
	}
	
	@Override
	public void render(Graphics2D g) {
		if (GameLogic.getState() == GameState.BATTLE && monster != null) {
			int currentHp = monster.getStats().getCurrentHp();
			int maxHp = monster.getStats().getMaxHp();
			double percent = (double) currentHp / (double) maxHp;
			
			// Draw surrounding box
			ImageRenderer.renderScaled(g, healthBox, x, y, scaleFactor);
			
			// Draw monster name
			g.setFont(Fonts.PIXEL_EMULATOR);
			g.setColor(Color.black);
			TextRenderer.render(g, monster.getData().getName(), x + (7 * scaleFactor), y + (10 * scaleFactor));
			
			// Draw actual health bar
			int w = 52 * scaleFactor;
			g.setColor(Color.CYAN);
			g.fillRect(x + (17 * scaleFactor), y + (15 * scaleFactor), (int) (w * percent), (4 * scaleFactor));
			
			// Draw health in numbers
			g.setColor(Color.BLACK);
			g.setFont(Fonts.PIXEL_EMULATOR.deriveFont(Fonts.TEXT_SIZE - 2f)); // make health a bit smaller than the name
			TextRenderer.render(g, monster.getStats().getCurrentHp() +"/"+ monster.getStats().getMaxHp(), x + (7 * scaleFactor), y + (26 * scaleFactor));
		}
	}
}
