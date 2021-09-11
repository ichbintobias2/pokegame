package de.tobias.pokegame.frontend.constants;

import java.awt.image.BufferedImage;

import de.gurkenlabs.litiengine.resources.Resources;

public abstract class Images {
	
	// all images in the game will be displayed their original size * scaleFactor on the screen
	public static final double SCALE_FACTOR = 4.5;
	
	private final static String basePath = "src/main/resources/sprites/";
	public static final BufferedImage GAME_ICON = Resources.images().get(basePath+"icon.png");
	public static final BufferedImage DIALOG_1 = Resources.images().get(basePath+"dialog/dialog1.png");
	public static final BufferedImage HEALTH_BAR_1 = Resources.images().get(basePath+"battle/healthbar1.png");
	public static final BufferedImage SIGN_1 = Resources.images().get(basePath+"locationsign/locationsign1.png");
	public static final BufferedImage INTERACT_1 = Resources.images().get(basePath+"interactbutton/interactbutton1.png");
	
	public static final BufferedImage TEAM_BG_1 = Resources.images().get(basePath+"team/teamview1.png");
	public static final BufferedImage TEAM_MODULE_1 = Resources.images().get(basePath+"team/module1.png");
	
	public static final BufferedImage BOX_BG_1 = Resources.images().get(basePath+"box/background.png");
	
	public static BufferedImage getMonsterIcon(int monsterId) {
		return Resources.images().get(basePath+"monsters/icons/"+ monsterId +".png");
	}
}
