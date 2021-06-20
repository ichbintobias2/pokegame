package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.ui.LocationSign;

public class LocationTriggers {
	
	public static void changeLocation(String targetMap, String targetSpawn) {
		Game.window().getRenderComponent().fadeOut(750);
		
		Game.loop().perform(1000, () -> {
			Game.world().loadEnvironment(targetMap);
			Player.instance().setLocation(Game.world().environment().getSpawnpoint(targetSpawn).getLocation());
			Game.world().setCamera(new PositionLockCamera(Player.instance()));
			
			LocationSign.instance().setVisibleWithText(targetMap);
		});
	}
}
