package de.tobias.pokegame.frontend;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.tobias.pokegame.frontend.entities.Player;

public class GameLogic {

	public static void init() {
		Camera camera = new PositionLockCamera(Player.instance());
		camera.setClampToMap(true);
		Game.world().setCamera(camera);

		Game.world().setGravity(0);

		Game.world().onLoaded(e -> {
			Spawnpoint enter = e.getSpawnpoint("spawn");

			if (enter != null) {
				enter.spawn(Player.instance());
			}
		});
	}
}
