package de.tobias.pokegame.frontend;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.backend.calc.DamageCalc;
import de.tobias.pokegame.backend.calc.LevelCalc;
import de.tobias.pokegame.backend.calc.TypeCalc;
import de.tobias.pokegame.backend.entities.monster.BaseMonster;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.entities.player.Registry;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.backend.persistence.Savegame;
import de.tobias.pokegame.backend.wild.MonsterGenerator;
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.constants.Images;
import de.tobias.pokegame.frontend.entities.Monster;
import de.tobias.pokegame.frontend.entities.NPC;
import de.tobias.pokegame.frontend.entities.Player;
import de.tobias.pokegame.frontend.entities.controllers.EnemyMonsterController;
import de.tobias.pokegame.frontend.menu.AttackMenu;
import de.tobias.pokegame.frontend.menu.BattleMenu;
import de.tobias.pokegame.frontend.menu.ItemMenu;
import de.tobias.pokegame.frontend.menu.PauseMenu;
import de.tobias.pokegame.frontend.screens.BattleScreen;
import de.tobias.pokegame.frontend.ui.Dialog;
import de.tobias.pokegame.frontend.ui.HealthBar;
import de.tobias.pokegame.frontend.ui.ImageComponent;
import lombok.Getter;
import lombok.Setter;

public class BattleControl {
	
	@Getter @Setter private static Monster playerMonster;
	@Getter @Setter private static Monster enemyMonster;
	@Getter @Setter private static Monster lastCaught;
	
	@Getter private static CurrentMonster encounter;
	@Getter private static int givenXp;
	@Getter private static int damageToTake;
	
	private static NPC opponent;
	private static boolean trainerBattle;
	
	public static void startWildBattle(int registryNumber) {
		trainerBattle = false;
		encounter = MonsterGenerator.generateMonster(registryNumber);
		enemyMonster = new Monster(encounter);
		
		// Adding dialogue
		List<String> lines = new ArrayList<>();
		lines.add("A wild" + encounter.getName() + " appears!");
		lines.add("Choose your attack!");
		lines.add("[ask for input]");
		Dialog.instance().addToQueue(lines);
		
		startBattle();
	}
	
	public static void startTrainerBattle(NPC opposingTrainer) {
		trainerBattle = true;
		opponent = opposingTrainer;
		enemyMonster = new Monster(NitriteManager.getCurrentMonsterById(opponent.getTeamIds().get(0)));
		
		// Adding dialogue
		List<String> lines = new ArrayList<>();
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
			ItemMenu.instance().setVisible(false);
			ItemMenu.instance().setEnabled(false);
			Game.world().loadEnvironment("battle");
			Dialog.instance().setVisible(true);
			Dialog.instance().enable(true);
			GameLogic.setState(GameState.BATTLE);
		});
		
		playerMonster = new Monster(Savegame.getPlayerTeam().get(0));
		
		HealthBar hbPlayer = new HealthBar(800, 700, playerMonster);
		HealthBar hbEnemy = new HealthBar(40, 25, enemyMonster);
		
		BattleScreen.addToScreen(hbPlayer);
		BattleScreen.addToScreen(hbEnemy);
		
		int playerMonsterId = playerMonster.getData().getRegistryNumber();
		ImageComponent playerIc = new ImageComponent(40, 50, Images.getMonsterSprite(playerMonsterId), 2);
		BattleScreen.addToScreen(playerIc);
		
		int enemyMonsterId = enemyMonster.getData().getRegistryNumber();
		ImageComponent enemyIc = new ImageComponent(180, 25, Images.getMonsterSprite(enemyMonsterId), 2);
		BattleScreen.addToScreen(enemyIc);
		
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
			
			// clearing encounters
			enemyMonster = null;
			lastCaught = null;
		});
	}
	
	public static void performPlayerAttack(int i) {
		String attackName = playerMonster.getData().getAttacks().get(i);
		
		int playerAttack = playerMonster.getStats().getCurrentAtk();
		int enemyDefense = playerMonster.getStats().getCurrentDef();
		int monsterLevel = playerMonster.getData().getLevel();
		int currentHp = enemyMonster.getStats().getCurrentHp();
		
		int baseDamage = new DamageCalc(playerAttack, enemyDefense, monsterLevel).calculateDamage(attackName);
		double typeMultiplier = new TypeCalc(attackName, enemyMonster.getData().getTypes()).getTypeMultiplier();
		int finalDamage = (int) (baseDamage * typeMultiplier);
		
		Dialog.instance().addToQueue(""); // Needed to keep queue not empty
		Dialog.instance().addToQueue(playerMonster.getData().getName() +" setzt "+ attackName +" ein!");
		String effectivityString = new TypeCalc(attackName, enemyMonster.getData().getTypes()).getEffectivenessAsString();
		
		Dialog.instance().addToQueue("[enemy damage]");
		Dialog.instance().addToQueue(effectivityString);
		
		if (currentHp <= finalDamage) {
			damageToTake = currentHp;
			onEnemyMonsterDefeated();
		} else {
			damageToTake = finalDamage;
			passTurn();
		}
	}
	
	private static String performEnemyAttack() {
		EnemyMonsterController emc = new EnemyMonsterController(enemyMonster);
		String attackName = emc.decideEnemyAttack();
		
		// TODO currently not all decision paths of decideEnemyAttack() are implemented.
		// The not yet implemented will return null which would cause an exception in DamageCalc.
		// therefore the attack name will be hard coded for now
		if (attackName == null) {
			attackName = "Base Fire";
		}
		
		int enemyAttack = enemyMonster.getStats().getCurrentAtk();
		int playerDefense = playerMonster.getStats().getCurrentDef();
		int monsterLevel = enemyMonster.getData().getLevel();
		int currentHp = playerMonster.getStats().getCurrentHp();
		
		int baseDamage = new DamageCalc(enemyAttack, playerDefense, monsterLevel).calculateDamage(attackName);
		double typeMultiplier = new TypeCalc(attackName, playerMonster.getData().getTypes()).getTypeMultiplier();
		int finalDamage = (int) (baseDamage * typeMultiplier);
		
		if (currentHp <= finalDamage) {
			damageToTake = currentHp;
			onPlayerMonsterDefeated();
		} else {
			damageToTake = finalDamage;
		}
		return attackName;
	}
	
	public static void passTurn() {  // TODO i18n
		// weather and other turn based events can be checked here
		
		String enemyAttackName = performEnemyAttack();
		
		Dialog.instance().addToQueue(enemyMonster.getData().getName() +" (Gegner) setzt "+ enemyAttackName +" ein!");
		String effectivityString = new TypeCalc(enemyAttackName, playerMonster.getData().getTypes()).getEffectivenessAsString();
		
		Dialog.instance().addToQueue("[player damage]");
		Dialog.instance().addToQueue(effectivityString);
		
		Dialog.instance().addToQueue("Was soll "+ playerMonster.getData().getName() +" tun?");
		Dialog.instance().addToQueue("[ask for input]");
		
		// asking for new input
		AttackMenu.instance().setVisible(false);
		BattleMenu.instance().setVisible(true);
		BattleMenu.instance().setEnabled(false);
		Dialog.instance().enable(true);
		Dialog.instance().nextLine();
	}
	
	public static void catchWild() {
		if (encounter != null) {
			// TODO ask where the monster should be saved (either box or team)
			
			int encounterRegistryNr = encounter.getRegistryNumber();
			Registry registry = NitriteManager.getRegistry();
			registry.setSeen(encounterRegistryNr); // set seen to true in case it was not true already
			if (!registry.checkCaught(encounterRegistryNr)) {
				lastCaught = enemyMonster;
				Dialog.instance().addToQueue("Unlocked new registry entry!");
				Dialog.instance().addToQueue("[show catch screen]");
				registry.setCaught(encounterRegistryNr);
			}
			
			Dialog.instance().addToQueue(encounter.getName() +" was caught!");
			gainXp();
			// Dialog.instance().addToQueue("Do you want to give a nickname to"+ encounter.getName() +"?");
			// Dialog.instance().addToQueue("[input]");
			Dialog.instance().addToQueue(encounter.getName() +" was stored in a box!");
			Dialog.instance().addToQueue("[stop battle]");
			
			Player.instance().team().add(encounter);
			encounter = null;
		}
	}
	
	private static void gainXp() {
		// calculate given xp
		BaseMonster enemyBase;
		int enemyLevel;
		boolean trainerBattle = false;
		if (encounter != null && enemyMonster == null) { // test if it was a wild battle
			enemyBase = NitriteManager.getBaseMonsterByRegistryNr(encounter.getRegistryNumber());
			enemyLevel = encounter.getLevel();
		} else if (enemyMonster != null && encounter == null) { // if it was a trainer battle
			enemyBase = NitriteManager.getBaseMonsterByRegistryNr(enemyMonster.getData().getRegistryNumber());
			enemyLevel = enemyMonster.getData().getLevel();
			trainerBattle = true;
		} else return; // if neither wild nor trainer e.g. when an error happened
		int xpBase = enemyBase.getXpBase();
		givenXp = LevelCalc.getXpFromBase(xpBase, enemyLevel, trainerBattle);
		
		Dialog.instance().addToQueue(playerMonster.getData().getName() +" gained "+ givenXp +" XP!");
		Dialog.instance().addToQueue("[gain xp]");
	}
	
	private static void onPlayerMonsterDefeated() {
		// TODO
	}
	
	private static void onEnemyMonsterDefeated() {
		AttackMenu.instance().setEnabled(false);
		Dialog.instance().enable(true);
		gainXp();
		Dialog.instance().addToQueue(""); // TODO this line needs to be removed but the [stop battle] currently only works this way
		Dialog.instance().addToQueue("[stop battle]");
		// TODO either switch or end battle
	}
}
