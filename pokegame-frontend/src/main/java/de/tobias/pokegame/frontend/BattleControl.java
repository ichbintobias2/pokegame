package de.tobias.pokegame.frontend;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.menu.AttackMenu;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import de.tobias.pokegame.frontend.screens.Dialog;

public class BattleControl {

	public static void startBattle() {
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(true);
			
			Game.screens().display("BATTLE");
			Game.world().loadEnvironment("battle");
			GameLogic.setState(GameState.BATTLE);
		});
		
		List<String> lines = new ArrayList<String>();
		lines.add("Trainer wants to battle!");
		lines.add("Choose your monster!");
		lines.add("[endbattle]");
		Dialog.addNpcLines(lines);
		AttackMenu.instance().setEnabled(false);
	}
	
	public static void stopBattle() {
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(false);
			
			Game.screens().display("INGAME");
			Game.world().loadEnvironment("level1"); // TODO do not hardcode
			GameLogic.setState(GameState.TALKING);
			PauseMenu.instance().update();
		});
	}
}
