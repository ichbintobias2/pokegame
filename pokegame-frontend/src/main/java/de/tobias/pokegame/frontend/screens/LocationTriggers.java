package de.tobias.pokegame.frontend.screens;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.ui.LocationSign;

public class LocationTriggers {
	
	public static void changeLocation(String whereto) {
		Game.window().getRenderComponent().fadeOut(1500);
		
		Game.loop().perform(2500, () -> {
			Game.world().loadEnvironment(whereto);
			Player.instance().setLocation(Game.world().environment().getSpawnpoint("spawn").getLocation());
			Game.world().setCamera(new PositionLockCamera(Player.instance()));
			
			LocationSign.instance().setVisibleWithText(whereto);
		});
	}
}
