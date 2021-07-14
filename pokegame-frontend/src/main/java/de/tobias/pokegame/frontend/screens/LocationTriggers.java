package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.ui.LocationSign;

public class LocationTriggers {
	
	public static void changeLocation(String targetMap, String targetSpawn) {
		Game.window().getRenderComponent().fadeOut(500);
		
		Game.loop().perform(500, () -> {
			Game.world().loadEnvironment(targetMap);
			Game.world().environment().getSpawnpoint(targetSpawn).spawn(Player.instance());
			Game.world().setCamera(new PositionLockCamera(Player.instance()));
			
			LocationSign.instance().setVisibleWithText(targetMap);
		});
	}
}
