package de.tobias.pokegame.frontend.screens;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.GuiComponent;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.entities.NPC;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.enums.SoundControl;

public class Dialog extends GuiComponent {
	// private final BufferedImage DIALOG = Imaging.scale(Resources.images().get("dialog.png"), 5.0);
	private final int PADDING = 10;
	private static int lineNr = 0;
	private static List<String> npcLines;

	protected Dialog() {
		super(0, 0, Game.window().getResolution().getWidth(), Game.window().getResolution().getHeight());
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		if (GameLogic.getState() != GameState.TALKING) {
			return;
		} else renderDialog(g);
	}

	private void renderDialog(Graphics2D g) {
		int w = (int) (Game.window().getResolution().getWidth() - (PADDING * 2));
		int h = (int) (Game.window().getResolution().getHeight() / 5);
		
		int x = PADDING;
		int y = (int) (Game.window().getResolution().getHeight() - h - PADDING);
		
		g.draw(new Rectangle(x, y, w, h));
		g.drawString(npcLines.get(lineNr), x, y + 10);
	}
	
	public static void nextLine() {
		Game.audio().playSound(SoundControl.Dialog);
		
		if (GameLogic.getState() == GameState.TALKING) {
			if (lineNr < npcLines.size() - 1) {
				lineNr += 1;
			} else {
				GameLogic.setState(GameState.INGAME);
				lineNr = 0;
			}
		} 
	}
	
	public static void startDialog() {
		Game.audio().playSound(SoundControl.Dialog);
		GameLogic.setState(GameState.TALKING);
	}
	
	public static void setNpc(NPC npc) {
		npcLines = npc.getDialogLines();
	}
}
