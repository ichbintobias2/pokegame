package de.tobias.pokegame.frontend.constants;

import java.awt.Font;

import de.gurkenlabs.litiengine.resources.Resources;

public abstract class Fonts {
	
	public static float TEXT_SIZE = 18f;
	public static Font MENU_FONT;
	public static Font PIXEL_EMULATOR = Resources.fonts().get("src/main/resources/fonts/PixelEmulator.ttf").deriveFont(TEXT_SIZE);
}
