package de.tobias.pokegame.frontend.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;

public class HealthBar extends GuiComponent {
	private Monster monster;
	private int x;
	private int y;
	
	public HealthBar(int x, int y, Monster monster) {
		super(x, y);
		this.monster = monster;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render(Graphics2D g) {
		if (GameLogic.getState() == GameState.BATTLE) {
			int w = (int) (Game.window().getResolution().getWidth() / 4);
			int h = (int) (Game.window().getResolution().getHeight() / 8);
			
			int currentHp = monster.getCurrentHp();
			int maxHp = monster.getMaxHp();
			double percent = (double) currentHp / (double) maxHp;

			// Draw surrounding box
			g.setColor(Color.WHITE);
			g.fillRect(x, y, w, h);
			
			g.setColor(Color.BLACK);
			g.drawRect(x, y, w, h);
			
			// Draw monster name
			g.setFont(new Font("", Font.PLAIN, 24));
			g.drawString("Placeholder1", x + 20, y + 20);
			
			// Draw actual health bar
			g.drawRect(x, y + 30, w, 30);
			
			g.setColor(Color.CYAN);
			g.fillRect(x, y + 30, (int) (w * percent), 30);
			
			// Draw health in numbers
			g.setColor(Color.BLACK);
			g.drawString(monster.getCurrentHp() +"/"+ monster.getMaxHp(), x, y + 85);
		}
	}
}
