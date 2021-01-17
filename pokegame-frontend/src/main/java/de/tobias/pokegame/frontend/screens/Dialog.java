package de.tobias.pokegame.frontend.screens;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.enums.SoundControl;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class Dialog extends GuiComponent {
	private final int PADDING = 10;
	private List<String> queue = new ArrayList<String>();
	private String currentLine = "";
	
	private static Dialog instance;
	
	private boolean render = false;
	private boolean enabled = false;

	private Dialog() {
		super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
	}
	
	public static Dialog instance() {
		if (instance == null) {
			instance = new Dialog();
		}
		
		return instance;
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		if (render) {
			int w = (int) (Game.window().getResolution().getWidth() - (PADDING * 2));
			int h = (int) (Game.window().getResolution().getHeight() / 5);
			
			int x = PADDING;
			int y = (int) (Game.window().getResolution().getHeight() - h - PADDING);
			
			g.draw(new Rectangle(x, y, w, h));
			g.drawString(currentLine, x, y + 10);
		}
	}
	
	public void nextLine() {
		Game.audio().playSound(SoundControl.Dialog);
		
		if (enabled) {
			if (queue.size() > 0) {
				if ("[battle]".equals(queue.get(1))) {
					setVisible(false);
					queue.remove(1);
					BattleControl.startBattle();
				} else if ("[ask for input]".equals(queue.get(1))) {
					enable(false);
					queue.remove(1);
					BattleMenu.instance().setEnabled(true);
				} else if ("[enemy attack]".equals(queue.get(1))) {
					BattleControl.performEnemyAttack();
					queue.remove(1);
				}
				
				queue.remove(0);
				if (queue.size() != 0) currentLine = queue.get(0);
			} else {
				GameLogic.setState(GameState.INGAME);
				enable(false);
				setVisible(false);
			}
		} 
	}
	
	public void addToQueue(String line) {
		queue.add(line);
	}
	
	public void addToQueue(List<String> lines) {
		queue.addAll(lines);
	}
	
	@Override
	public void setVisible(boolean visible) {
		currentLine = queue.get(0);
		render = visible;
	}
	
	public void enable(boolean enable) {
		enabled = enable;
	}
}
