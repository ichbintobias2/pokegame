package de.tobias.pokegame.frontend.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.constants.SoundControl;
import de.tobias.pokegame.frontend.entities.NPC;
import de.tobias.pokegame.frontend.menu.BattleMenu;

public class Dialog extends GuiComponent {
	
	private List<String> queue = new ArrayList<String>();
	private String currentLine = "";
	
	private static Dialog instance;
	
	private boolean render = false;
	private boolean enabled = false;
	
	private double scaleFactor = Game.window().getHeight() / 240;
	private BufferedImage dialogImage = Images.DIALOG_1;
	
	private double x = (Game.window().getWidth() / 2.0) - ((dialogImage.getWidth() * scaleFactor) / 2.0);
	private double y =  Game.window().getHeight() * 0.75;
	
	private NPC dialogPartner;
	
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
			g.setFont(Fonts.PIXEL_EMULATOR);
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
					BattleControl.startTrainerBattle(dialogPartner);
				} else if ("[ask for input]".equals(queue.get(1))) {
					enable(false);
					queue.remove(1);
					BattleMenu.instance().setEnabled(true);
				} else if ("[stop battle]".equals(queue.get(1))) {
					BattleControl.stopBattle();
					enable(false);
					setVisible(false);
					queue.remove(1);
				} else if ("[enable battle menu]".equals(queue.get(1))) {
					BattleMenu.instance().setVisible(true);
					BattleMenu.instance().setEnabled(true);
					enable(false);
					queue.remove(1);
				} else if ("[on monster caught]".equals(queue.get(1))) {
					BattleControl.catchWild();
					queue.remove(1);
				} else if ("[gain xp]".equals(queue.get(1))) {
					BattleControl.getPlayerMonster().gainXp(BattleControl.getGivenXp());
					queue.remove(1);
				} else if ("[enemy damage]".equals(queue.get(1))) {
					BattleControl.getEnemyMonster().getStats().receiveDamage(BattleControl.getDamageToTake());
					queue.remove(1);
				} else if ("[player damage]".equals(queue.get(1))) {
					BattleControl.getPlayerMonster().getStats().receiveDamage(BattleControl.getDamageToTake());
				} else if ("[show catch screen]".equals(queue.get(1))) {
					Game.screens().display("CATCH");
					Game.world().loadEnvironment("battle");
					
					queue.remove(1);
				}
				
				queue.remove(0);
				if (queue.size() != 0) currentLine = queue.get(0);
			} else {
				GameLogic.setState(GameState.INGAME);
				enable(false);
				setVisible(false);
				if (queue.size() > 0) queue.remove(0);
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
		if (queue.size() > 0) currentLine = queue.get(0);
		render = visible;
	}
	
	public void enable(boolean enable) {
		enabled = enable;
	}
	
	public void setDialogPartner(NPC npc) {
		dialogPartner = npc;
	}
	
	public void clearQueue() {
		queue.clear();
	}
}
