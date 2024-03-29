package de.tobias.pokegame.frontend;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.screens.*;

public class PokeGameApplication {
	
	public static void main(String[] args) {
		Game.info().setName("FAKEMON T");
		Game.info().setSubTitle("");
		Game.info().setVersion("v0.0.1");
		Game.info().setWebsite("");
		Game.info().setDescription("A small RPG game about catching monsters by Tobias");
		
		Game.init(args);
		
		Game.window().setIcon(Images.GAME_ICON);
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
		Game.screens().add(new TeamScreen());
		Game.screens().add(new BoxScreen());
		Game.screens().add(new RegistryScreen());
		
		// loading the main menu
		Game.screens().display("MAIN");
		
		Game.start();
	}
}
