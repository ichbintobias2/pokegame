package de.tobias.pokegame.frontend;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.menu.AttackMenu;
import de.tobias.pokegame.frontend.menu.BattleMenu;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import de.tobias.pokegame.frontend.screens.Dialog;
import lombok.Getter;
import lombok.Setter;

public class BattleControl {
	@Getter @Setter
	private static boolean isPlayerAtTurn = true;

	public static void startBattle() {
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(true);
			
			Game.screens().display("BATTLE");
			Game.world().loadEnvironment("battle");
			Dialog.instance().setVisible(true);
			GameLogic.setState(GameState.BATTLE);
		});
		
		List<String> lines = new ArrayList<String>();
		lines.add("Trainer wants to battle!");
		lines.add("Choose your attack!");
		lines.add("[ask for input]");
		Dialog.instance().addToQueue(lines);
		AttackMenu.instance().setEnabled(false);
	}
	
	public static void stopBattle() {
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(false);
			
			Game.screens().display("INGAME");
			Game.world().loadEnvironment("level1"); // TODO do not hardcode
			// Dialog.instance().setVisible(false);
			GameLogic.setState(GameState.TALKING);
			PauseMenu.instance().update();
		});
	}
	
	public static void passTurn() {  // TODO i18n
		// weather and other turn based events can be checked here
		
		// dialog for own chosen attack
		String attackName = "Sample"; // TODO get Name from Attack class
		String monsterName = "Placeholder1"; // TODO get Name from PlayerMonster class
		
		Dialog.instance().addToQueue(""); // Yes this is needed
		Dialog.instance().addToQueue(monsterName+" setzt "+attackName+" ein!");
		Dialog.instance().addToQueue("Es ist sehr effektiv!");
		
		// dialog for enemy attack
		String enemyAttack = "Sample"; // TODO also from attack class
		String enemyMonster = "Placeholder2"; // TODO get from EnemyMonster class
		
		Dialog.instance().addToQueue(enemyMonster+" setzt "+enemyAttack+" ein!");
		Dialog.instance().addToQueue("Es ist sehr effektiv!");
		Dialog.instance().addToQueue("Was soll Placeholder1 tun?");
		Dialog.instance().addToQueue("[ask for input]");
		
		// asking for new input
		AttackMenu.instance().setVisible(false);
		BattleMenu.instance().setVisible(true);
		BattleMenu.instance().setEnabled(false);
		Dialog.instance().enable(true);
		Dialog.instance().nextLine();
	}
}
