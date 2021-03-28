package de.tobias.pokegame.frontend;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.backend.calc.DamageCalc;
import de.tobias.pokegame.backend.calc.TypeCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.backend.wild.MonsterGenerator;
import de.tobias.pokegame.frontend.entities.EnemyMonster;
import de.tobias.pokegame.frontend.entities.EnemyMonsterController;
import de.tobias.pokegame.frontend.entities.PlayerMonster;
import de.tobias.pokegame.frontend.entities.Savegame;
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
	private static String lastPlayerAttack;
	private static String lastEnemyAttack;
	
	public static void startWildBattle(int registryNumber) {
		CurrentMonster encounter = MonsterGenerator.generateMonster(registryNumber);
		EnemyMonster.instance().set(encounter);
		
		// Adding dialogue
		List<String> lines = new ArrayList<String>();
		lines.add("A wild" + encounter.getName() + " appears!");
		lines.add("Choose your attack!");
		lines.add("[ask for input]");
		Dialog.instance().addToQueue(lines);
		
		startBattle();
	}
	
	public static void startTrainerBattle(String trainerName) {
		// TODO when the battle is starting you will have to determine which monsters will be
		// sent into battle by the opposing trainer
		EnemyMonster.instance().set(NitriteManager.getCurrentMonsterByName("placeholder"));
		
		// Adding dialogue
		List<String> lines = new ArrayList<String>();
		lines.add("Trainer wants to battle!");
		lines.add("Choose your attack!");
		lines.add("[ask for input]");
		Dialog.instance().addToQueue(lines);
		
		startBattle();
	}
	
	private static void startBattle() {
		Game.window().getRenderComponent().fadeOut(1500);
		
		CurrentMonster firstFromTeam = Savegame.getPlayerTeam().get(0);
		PlayerMonster.instance().set(firstFromTeam);
		
		AttackMenu.instance().set(PlayerMonster.instance());
		
		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(true);
			
			Game.screens().display("BATTLE");
			Game.world().loadEnvironment("battle");
			Dialog.instance().setVisible(true);
			Dialog.instance().enable(true);
			GameLogic.setState(GameState.BATTLE);
		});
		
		AttackMenu.instance().setEnabled(false);
	}
	
	public static void stopBattle() {
		Game.window().getRenderComponent().fadeOut(1500);

		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(false);
			
			Game.screens().display("INGAME");
			Game.world().loadEnvironment("level1"); // TODO do not hardcode
			Dialog.instance().enable(true);
			GameLogic.setState(GameState.TALKING);
			PauseMenu.instance().update();
		});
	}
	
	public static void performPlayerAttack(int i) {
		lastPlayerAttack = PlayerMonster.instance().getCm().getAttacks().get(i);
		
		int playerAttack = PlayerMonster.instance().getStats().getCurrentAtk();
		int enemyDefense = EnemyMonster.instance().getStats().getCurrentDef();
		int monsterLevel = PlayerMonster.instance().getCm().getLevel();
		int currentHp = EnemyMonster.instance().getStats().getCurrentHp();
		
		int baseDamage = new DamageCalc(playerAttack, enemyDefense, monsterLevel).calculateDamage(lastPlayerAttack);
		double typeMultiplicator = new TypeCalc(lastPlayerAttack, EnemyMonster.instance().getCm().getTypes()).getTypeMultiplier();
		double finalDamage = baseDamage * typeMultiplicator;
		
		if (currentHp <= finalDamage) {
			EnemyMonster.instance().getStats().receiveDamage(currentHp);
			
			BattleControl.stopBattle(); // TODO should cause switch instead
		} else {
			EnemyMonster.instance().getStats().receiveDamage(finalDamage);
			passTurn();
		}
	}
	
	public static void performEnemyAttack() {		
		int enemyAttack = EnemyMonster.instance().getStats().getCurrentAtk();
		int playerDefense = PlayerMonster.instance().getStats().getCurrentDef();
		int monsterLevel = EnemyMonster.instance().getCm().getLevel();
		int currentHp = PlayerMonster.instance().getStats().getCurrentHp();
		
		int baseDamage = new DamageCalc(enemyAttack, playerDefense, monsterLevel).calculateDamage(lastEnemyAttack);
		double typeMultiplicator = new TypeCalc(lastEnemyAttack, PlayerMonster.instance().getCm().getTypes()).getTypeMultiplier();
		double finalDamage = baseDamage * typeMultiplicator;
		
		if (currentHp <= finalDamage) {
			PlayerMonster.instance().getStats().receiveDamage(currentHp);
			
			BattleControl.stopBattle(); // TODO should cause switch instead
		} else {
			PlayerMonster.instance().getStats().receiveDamage(finalDamage);
		}
	}
	
	public static void passTurn() {  // TODO i18n
		// weather and other turn based events can be checked here
		
		// dialog for own chosen attack
		String attackName = lastPlayerAttack;
		String monsterName = PlayerMonster.instance().getCm().getName();
		
		Dialog.instance().addToQueue(""); // Yes this is needed
		Dialog.instance().addToQueue(monsterName+" setzt "+attackName+" ein!");
		String effectString1 = new TypeCalc(lastPlayerAttack, EnemyMonster.instance().getCm().getTypes()).getEffectivenessAsString();
		Dialog.instance().addToQueue(effectString1);
		
		// dialog for enemy attack
		String enemyMonster = EnemyMonster.instance().getCm().getName();
		
		lastEnemyAttack = EnemyMonsterController.instance().decideEnemyAttack();
		
		// TODO currently not all decision paths of decideEnemyAttack() are implemented.
		// The not yet implemented will return null which would cause an exception in DamageCalc.
		// therefore the attack name will be hard coded for now
		if (lastEnemyAttack == null) {
			lastEnemyAttack = "Base Fire";
		}
		
		Dialog.instance().addToQueue("[enemy attack]");
		Dialog.instance().addToQueue(enemyMonster+" (Gegner) setzt "+lastEnemyAttack+" ein!");
		String effectString2 = new TypeCalc(lastEnemyAttack, PlayerMonster.instance().getCm().getTypes()).getEffectivenessAsString();
		Dialog.instance().addToQueue(effectString2);
		Dialog.instance().addToQueue("Was soll "+monsterName+" tun?");
		Dialog.instance().addToQueue("[ask for input]");
		
		// asking for new input
		AttackMenu.instance().setVisible(false);
		BattleMenu.instance().setVisible(true);
		BattleMenu.instance().setEnabled(false);
		Dialog.instance().enable(true);
		Dialog.instance().nextLine();
	}
}
