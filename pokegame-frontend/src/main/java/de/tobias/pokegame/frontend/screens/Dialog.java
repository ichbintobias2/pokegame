package de.tobias.pokegame.frontend.screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.Fonts;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.enums.SoundControl;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class Dialog extends GuiComponent {
	
	private List<String> queue = new ArrayList<String>();
	private String currentLine = "";
	
	private static Dialog instance;
	
	private boolean render = false;
	private boolean enabled = false;
	
	private double scaleFactor = Game.window().getHeight() / 240;
	private BufferedImage dialogImage = Resources.images().get("src/main/resources/sprites/dialog/dialog1.png");
	
	private double x = (Game.window().getWidth() / 2.0) - ((dialogImage.getWidth() * scaleFactor) / 2.0);
	private double y =  Game.window().getHeight() * 0.75;
	
	private Dialog() {
		super(0, 0);
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
			ImageRenderer.renderScaled(g, dialogImage, x, y, scaleFactor);
			g.setFont(Fonts.PIXEL_EMULATOR.deriveFont(18f));
			g.setColor(Color.BLACK);
			TextRenderer.renderWithLinebreaks(g, currentLine, x + 30, y + 30, (dialogImage.getWidth() * scaleFactor) - 35);
		}
	}
	
	public void nextLine() {
		Game.audio().playSound(SoundControl.Dialog);
		
		if (enabled) {
			if (queue.size() > 1) {
				if ("[battle]".equals(queue.get(1))) {
					setVisible(false);
					queue.remove(1);
					BattleControl.startTrainerBattle(""); // TODO trainer name dynamically
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
				queue.remove(0);
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
