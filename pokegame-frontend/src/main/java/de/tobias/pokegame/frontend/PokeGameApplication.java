package de.tobias.pokegame.frontend;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.frontend.screens.BattleScreen;
import de.tobias.pokegame.frontend.screens.IngameScreen;
import de.tobias.pokegame.frontend.screens.MainMenuScreen;
import de.tobias.pokegame.frontend.screens.OptionScreen;

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
		
		NitriteManager.init();
		PlayerInput.init();
		GameLogic.init();
		
		// adding all screens that will be used
		Game.screens().add(new IngameScreen());
		Game.screens().add(new MainMenuScreen());
		Game.screens().add(new OptionScreen());
		Game.screens().add(new BattleScreen());

		// loading the main menu
		Game.screens().display("MAIN");
		
		Game.start();
	}
}
