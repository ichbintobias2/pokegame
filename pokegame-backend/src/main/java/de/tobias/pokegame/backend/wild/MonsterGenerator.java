package de.tobias.pokegame.backend.wild;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import de.tobias.pokegame.backend.calc.LevelCalc;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

public class MonsterGenerator {
	
	public static CurrentMonster generateMonster(int registryNumber) {
		String name = ""; // TODO get name from DbMonster by registryNumber
		int level = 5; // TODO set level dynamically
		List<String> types = Arrays.asList("Fire");
		
		Random random = new Random();
		int dvHp = random.nextInt(32);
		int dvAttack = random.nextInt(32);
		int dvDefense = random.nextInt(32);
		int dvSpAtk = random.nextInt(32);
		int dvSpDef = random.nextInt(32);
		int dvSpeed = random.nextInt(32);
		
		// TODO hardcoded id
		int xp = LevelCalc.getXpForLevel(level);
		return new CurrentMonster(0, registryNumber, name, types, level, xp, getRandomNature(), getRandomAttacks(), getHeldItem(), null, level, null, dvHp, dvAttack, dvDefense, dvSpAtk, dvSpDef, dvSpeed, 0, 0, 0, 0, 0, 0);
	}
	
	private static String getHeldItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static List<String> getRandomAttacks() {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}
	
	private static String getRandomNature() {
		// TODO Auto-generated method stub
		return "Default";
	}
}
