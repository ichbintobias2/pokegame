package de.tobias.pokegame.frontend;

import java.awt.Font;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.gui.SpeechBubbleAppearance;
import de.tobias.pokegame.frontend.entities.NPC;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.screens.IngameScreen;

public class GameLogic {
	public static final Font MENU_FONT = null;
	public static SpeechBubbleAppearance SPEECH_BUBBLE_APPEARANCE;
	public static Font SPEECH_BUBBLE_FONT;
	public static int SPEECH_BUBBLE_TIME = 6000;
	public static String START_LEVEL = "level1";
	private static GameState state;

	public static void init() {
		// Register Objects here
		CreatureMapObjectLoader.registerCustomCreatureType(NPC.class);

		// Register Props here
		// PropMapObjectLoader.registerCustomPropType(HealthPot.class);

		Camera camera = new PositionLockCamera(Player.instance());
		camera.setClampToMap(false);
		Game.world().setCamera(camera);

		Game.world().onLoaded(e -> {
			if (e.getMap().getName().equals("title")) {
				return;
			}

			for (Prop prop : Game.world().environment().getProps()) {
				prop.setIndestructible(true);
			}

			Game.loop().perform(500, () -> Game.window().getRenderComponent().fadeIn(500));

			setState(GameState.INGAME);

			// spawn the player instance on the spawn point with the name "spawn"
			Spawnpoint enter = e.getSpawnpoint("spawn");
			
			if (enter != null) {
				enter.spawn(Player.instance());
			}
		});
	}

	public static void setState(GameState state) {
		GameLogic.state = state;

		if (getState() == GameState.PAUSED) {
			Game.loop().setTimeScale(0);
			IngameScreen.pauseMenu.setVisible(true);
		} else {
			Game.loop().setTimeScale(1);
			IngameScreen.pauseMenu.setVisible(false);
		}
	}

	public static GameState getState() {
		return state;
	}
	
	public static void showPauseMenu() {
		if (GameLogic.getState() == GameState.PAUSED) {
			GameLogic.setState(GameState.INGAME);
		} else if (GameLogic.getState() == GameState.INGAME) {
			GameLogic.setState(GameState.PAUSED);
		}
	}
}
