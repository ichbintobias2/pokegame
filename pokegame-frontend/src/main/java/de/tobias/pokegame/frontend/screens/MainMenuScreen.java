package de.tobias.pokegame.frontend.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.entities.enums.GameState;

public class MainMenuScreen extends Screen implements IUpdateable {
	private static final String COPYRIGHT = "placeholder";
	// private static final String MENUMUSIC = "";
	// private static final String CONFIRM = "";

	public long lastPlayed;

	private KeyboardMenu mainMenu;

	public MainMenuScreen() {
		super("MAIN");
	}

	@Override
	protected void initializeComponents() {
		final double centerX = Game.window().getResolution().getWidth() / 2.0;
		final double centerY = Game.window().getResolution().getHeight() * 1 / 2;
		final double buttonWidth = 450;

		this.mainMenu = new KeyboardMenu(centerX - buttonWidth / 2, centerY * 1.3, buttonWidth, centerY / 2,
				"Play", "Instructions", "Exit");

		this.getComponents().add(this.mainMenu);

		this.mainMenu.onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				this.startGame();
				break;
			case 1:
				this.showInstructions();
				break;
			case 2:
				this.exit();
				break;
			default:
				break;
			}
		});
	}

	@Override
	public void prepare() {
		this.mainMenu.setEnabled(true);
		super.prepare();
		
		Game.loop().attach(this);
		Game.window().getRenderComponent().setBackground(Color.BLACK);
		Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
		Game.world().loadEnvironment("mainmenu");
		
		this.mainMenu.incFocus();
	}

	@Override
	public void render(final Graphics2D g) {
		Game.world().environment().render(g);
		
		final double stringWidth = g.getFontMetrics().stringWidth(COPYRIGHT);
	    g.setColor(Color.WHITE);
	    final double centerX = Game.window().getResolution().getWidth() / 2.0;
	    TextRenderer.renderWithOutline(g, COPYRIGHT, centerX - stringWidth / 2, Game.window().getResolution().getHeight() * 19 / 20, Color.BLACK);
		
		super.render(g);
	}

	@Override
	public void suspend() {
		super.suspend();
		Game.loop().detach(this);
		Game.audio().stopMusic();
	}

	@Override
	public void update() {
		if (this.lastPlayed == 0) {
			// Game.audio().playMusic(Resources.sounds().get(MENUMUSIC));
			this.lastPlayed = Game.loop().getTicks();
		}
	}

	private void startGame() {
		this.mainMenu.setEnabled(false);
		// Game.audio().playSound(CONFIRM);
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.screens().display("INGAME-SCREEN");
			Game.world().loadEnvironment(GameLogic.START_LEVEL);
			GameLogic.setState(GameState.INGAME);
		});
	}
	
	private void showInstructions() {
		
	}
	
	private void exit() {
		System.exit(0);
	}
}
