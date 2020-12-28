package de.tobias.pokegame.frontend;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.enums.GameState;

public class BattleControl {

	public static void startBattle() {
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {			
			Game.screens().display("BATTLE");
			Game.world().loadEnvironment("battle");
			GameLogic.setState(GameState.BATTLE);
		});
	}
}
