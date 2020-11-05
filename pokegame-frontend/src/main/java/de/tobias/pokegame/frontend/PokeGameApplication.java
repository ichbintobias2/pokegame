package de.tobias.pokegame.frontend;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.frontend.screens.IngameScreen;

public class PokeGameApplication {

	public static void main(String[] args) {
		Game.info().setName("POKEMON T");
		Game.info().setSubTitle("");
		Game.info().setVersion("v0.0.1");
		Game.info().setWebsite("");
		Game.info().setDescription("A small Pokemon clone by Tobias");

		Game.init(args);

		Game.window().setIcon(Resources.images().get("src\\main\\resources\\sprites\\icon.png"));
		Game.graphics().setBaseRenderScale(4.001f);

		Resources.load("game.litidata");

		PlayerInput.init();
		GameLogic.init();

		// add the screens that will help you organize the different states of your game
		Game.screens().add(new IngameScreen());

		// load the first level (resources for the map were implicitly loaded from the game file)
		Game.world().loadEnvironment("level1");

		Game.start();
	}
}
