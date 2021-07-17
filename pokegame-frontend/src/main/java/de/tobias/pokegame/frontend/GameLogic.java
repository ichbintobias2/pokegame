package de.tobias.pokegame.frontend;

import java.util.Locale;
import java.util.ResourceBundle;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.entities.NPC;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import lombok.Getter;
import lombok.Setter;

public class GameLogic {

	public static String START_LEVEL = "level1";
	
	@Getter @Setter
	private static GameState state;
	
	@Getter @Setter
	private static Locale locale = Locale.getDefault();
	
	public static void init() {
		// Register Objects here
		CreatureMapObjectLoader.registerCustomCreatureType(NPC.class);
		
		// Register Props here
		// PropMapObjectLoader.registerCustomPropType(HealthPot.class);
		
		Camera camera = new PositionLockCamera(Player.instance());
		camera.setClampToMap(true);
		Game.world().setCamera(camera);
		
		Game.world().onLoaded(e -> {
			if (e.getMap().getName().equals("title")) {
				return;
			}
			
			for (Prop prop : Game.world().environment().getProps()) {
				prop.setIndestructible(true);
			}
			
			Game.loop().perform(500, () -> Game.window().getRenderComponent().fadeIn(500));
			
			// spawn the player instance on the spawn point with the name "spawn"
			Spawnpoint enter = e.getSpawnpoint("spawn");
			
			if (enter != null) {
				enter.spawn(Player.instance());
			}
		});
	}
	
	public static void showPauseMenu() {
		if (GameLogic.getState() == GameState.PAUSED) {
			GameLogic.setState(GameState.INGAME);
			PauseMenu.instance().update();
		} else if (GameLogic.getState() == GameState.INGAME) {
			GameLogic.setState(GameState.PAUSED);
			PauseMenu.instance().update();
		} else if (GameLogic.getState() == GameState.TEAM) {
			Game.screens().display("INGAME");
			GameLogic.setState(GameState.PAUSED);
			PauseMenu.instance().update();
		}
	}
	
	public static String localize(String text) {
		return ResourceBundle.getBundle("i18n/i18n", locale).getString(text);
	}
}
