package de.tobias.pokegame.frontend.screens;

import java.awt.Color;
import java.awt.Graphics2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.graphics.TextRenderer;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.constants.Fonts;
import de.tobias.pokegame.frontend.constants.SoundControl;
import de.tobias.pokegame.frontend.menu.MainMenu;

public class MainMenuScreen extends Screen implements IUpdateable {
	
	private static final String COPYRIGHT = GameLogic.localize("CopyrightNote");
	
	public long lastPlayed;
	
	public MainMenuScreen() {
		super("MAIN");
	}
	
	@Override
	protected void initializeComponents() {		
		this.getComponents().add(new MainMenu());
	}
	
	@Override
	public void prepare() {
		super.prepare();
		
		Game.loop().attach(this);
		Game.window().getRenderComponent().setBackground(Color.BLACK);
		Game.graphics().setBaseRenderScale(6f * Game.window().getResolutionScale());
		Game.world().loadEnvironment("mainmenu");
	}
	
	@Override
	public void render(final Graphics2D g) {
		Game.world().environment().render(g);
		
		double stringWidth = g.getFontMetrics().stringWidth(COPYRIGHT);
		double centerX = Game.window().getResolution().getWidth() / 2.0;
		
	    g.setColor(Color.WHITE);
	    g.setFont(Fonts.PIXEL_EMULATOR);
	    TextRenderer.renderWithOutline(g, COPYRIGHT, centerX - (stringWidth / 2), Game.window().getResolution().getHeight() * 19 / 20, Color.BLACK);
		
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
			Game.audio().playMusic(SoundControl.MenuMusic);
			this.lastPlayed = Game.loop().getTicks();
		}
	}
}
