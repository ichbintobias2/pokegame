package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.ui.Dialog;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CatchScreen extends GameScreen {
	
	public CatchScreen() {
		super("CATCH");
	}
	
	@Override
	protected void initializeComponents() {
		this.getComponents().add(Dialog.instance());
	}
	
	@Override
	public void render(final Graphics2D g) {
		super.render(g);
		int monsterId = BattleControl.getLastCaught().getData().getRegistryNumber();
		ImageRenderer.renderScaled(g, Images.getMonsterSprite(monsterId), 50, 50, Images.SCALE_FACTOR);
	}
}
