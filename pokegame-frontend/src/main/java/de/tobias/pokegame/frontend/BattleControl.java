package de.tobias.pokegame.frontend;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.backend.calc.DamageCalc;
import de.tobias.pokegame.backend.calc.TypeCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.backend.persistence.Savegame;
import de.tobias.pokegame.backend.wild.MonsterGenerator;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.entities.Monster;
import de.tobias.pokegame.frontend.entities.NPC;
import de.tobias.pokegame.frontend.entities.controllers.EnemyMonsterController;
import de.tobias.pokegame.frontend.menu.AttackMenu;
import de.tobias.pokegame.frontend.menu.BattleMenu;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import de.tobias.pokegame.frontend.screens.BattleScreen;
import de.tobias.pokegame.frontend.ui.Dialog;
import de.tobias.pokegame.frontend.ui.HealthBar;
import lombok.Getter;
import lombok.Setter;

public class BattleControl {
	
	@Getter @Setter
	private static boolean isPlayerAtTurn = true;
	private static String lastPlayerAttack;
	private static String lastEnemyAttack;
	
	private static NPC opponent;
	private static boolean trainerBattle;
	
	@Getter @Setter
	private static Monster playerMonster;
	
	@Getter @Setter
	private static Monster enemyMonster;
	
	public static void startWildBattle(int registryNumber) {
		trainerBattle = false;
		CurrentMonster encounter = MonsterGenerator.generateMonster(registryNumber);
		enemyMonster = new Monster(180, 25, encounter);
		Game.world().getEnvironment("battle").add(enemyMonster);
		
		// Adding dialogue
		List<String> lines = new ArrayList<String>();
		lines.add("A wild" + encounter.getName() + " appears!");
		lines.add("Choose your attack!");
		lines.add("[ask for input]");
		Dialog.instance().addToQueue(lines);
		
		startBattle();
	}
	
	public static void startTrainerBattle(NPC opposingTrainer) {
		trainerBattle = true;
		// TODO when the battle is starting you will have to determine which monsters will be
		// sent into battle by the opposing trainer
		opponent = opposingTrainer;
		enemyMonster = new Monster(180, 25, NitriteManager.getCurrentMonsterByName("placeholder"));
		Game.world().getEnvironment("battle").add(enemyMonster);
		
		// Adding dialogue
		List<String> lines = new ArrayList<String>();
		lines.add(opposingTrainer.getName()+" wants to battle!");
		lines.add("Choose your attack!");
		lines.add("[ask for input]");
		Dialog.instance().addToQueue(lines);
		
		startBattle();
	}
	
	private static void startBattle() {
		Game.window().getRenderComponent().fadeOut(1500);
		
		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(true);
			
			Game.screens().display("BATTLE");
			Game.world().loadEnvironment("battle");
			Dialog.instance().setVisible(true);
			Dialog.instance().enable(true);
			GameLogic.setState(GameState.BATTLE);
		});
		
		playerMonster = new Monster(40, 50, Savegame.getPlayerTeam().get(0));
		Game.world().getEnvironment("battle").add(playerMonster);
		
		// TODO this is a messy approach, it works but surely could be less complicated
		HealthBar hbPlayer = new HealthBar(800, 700, playerMonster);
		HealthBar hbEnemy = new HealthBar(40, 25, enemyMonster);
		
		BattleScreen.addToScreen(hbPlayer);
		BattleScreen.addToScreen(hbEnemy);
		
		AttackMenu.instance().set(playerMonster);
		
		AttackMenu.instance().setEnabled(false);
	}
	
	public static void stopBattle() {
		Game.window().getRenderComponent().fadeOut(1500);
		
		Game.loop().perform(2500, () -> {
			Game.world().camera().setClampToMap(false);
			
			Game.screens().display("INGAME");
			Game.world().loadEnvironment("level1"); // TODO do not hardcode
			PauseMenu.instance().update();
			if (trainerBattle) {
				Dialog.instance().addToQueue(opponent.getDialogLines(1));
				Dialog.instance().setVisible(true);
				Dialog.instance().enable(true);
				GameLogic.setState(GameState.TALKING);
			} else {
				Dialog.instance().setVisible(false);
				Dialog.instance().enable(false);
				GameLogic.setState(GameState.INGAME);
			}
			
		});
	}
	
	public static void performPlayerAttack(int i) {
		lastPlayerAttack = playerMonster.getData().getAttacks().get(i);
		
		int playerAttack = playerMonster.getStats().getCurrentAtk();
		int enemyDefense = playerMonster.getStats().getCurrentDef();
		int monsterLevel = playerMonster.getData().getLevel();
		int currentHp = enemyMonster.getStats().getCurrentHp();
		
		int baseDamage = new DamageCalc(playerAttack, enemyDefense, monsterLevel).calculateDamage(lastPlayerAttack);
		double typeMultiplicator = new TypeCalc(lastPlayerAttack, enemyMonster.getData().getTypes()).getTypeMultiplier();
		double finalDamage = baseDamage * typeMultiplicator;
		
		if (currentHp <= finalDamage) {
			enemyMonster.getStats().receiveDamage(currentHp);
			
			BattleControl.stopBattle(); // TODO should cause switch instead
		} else {
			enemyMonster.getStats().receiveDamage(finalDamage);
			passTurn();
		}
	}
	
	public static void performEnemyAttack() {		
		int enemyAttack = enemyMonster.getStats().getCurrentAtk();
		int playerDefense = playerMonster.getStats().getCurrentDef();
		int monsterLevel = enemyMonster.getData().getLevel();
		int currentHp = playerMonster.getStats().getCurrentHp();
		
		int baseDamage = new DamageCalc(enemyAttack, playerDefense, monsterLevel).calculateDamage(lastEnemyAttack);
		double typeMultiplicator = new TypeCalc(lastEnemyAttack, playerMonster.getData().getTypes()).getTypeMultiplier();
		double finalDamage = baseDamage * typeMultiplicator;
		
		if (currentHp <= finalDamage) {
			playerMonster.getStats().receiveDamage(currentHp);
			
			BattleControl.stopBattle(); // TODO should cause switch instead
		} else {
			playerMonster.getStats().receiveDamage(finalDamage);
		}
	}
	
	public static void passTurn() {  // TODO i18n
		// weather and other turn based events can be checked here
		
		// dialog for own chosen attack
		String attackName = lastPlayerAttack;
		String monsterName = playerMonster.getData().getName();
		
		Dialog.instance().addToQueue(""); // Yes this is needed
		Dialog.instance().addToQueue(monsterName+" setzt "+attackName+" ein!");
		String effectString1 = new TypeCalc(lastPlayerAttack, enemyMonster.getData().getTypes()).getEffectivenessAsString();
		Dialog.instance().addToQueue(effectString1);
		
		// dialog for enemy attack
		String enemyMonsterName = enemyMonster.getData().getName();
		
		EnemyMonsterController emc = new EnemyMonsterController(enemyMonster);
		lastEnemyAttack = emc.decideEnemyAttack();
		
		// TODO currently not all decision paths of decideEnemyAttack() are implemented.
		// The not yet implemented will return null which would cause an exception in DamageCalc.
		// therefore the attack name will be hard coded for now
		if (lastEnemyAttack == null) {
			lastEnemyAttack = "Base Fire";
		}
		
		Dialog.instance().addToQueue("[enemy attack]");
		Dialog.instance().addToQueue(enemyMonsterName +" (Gegner) setzt "+lastEnemyAttack+" ein!");
		String effectString2 = new TypeCalc(lastEnemyAttack, playerMonster.getData().getTypes()).getEffectivenessAsString();
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
