package de.tobias.pokegame.frontend.enums;

import java.awt.Font;

import de.gurkenlabs.litiengine.resources.Resources;

public class Fonts {
	
	public static float textSize = 18f;
	public static Font PIXEL_EMULATOR = Resources.fonts().get("src/main/resources/fonts/PixelEmulator.ttf").deriveFont(textSize);
}
