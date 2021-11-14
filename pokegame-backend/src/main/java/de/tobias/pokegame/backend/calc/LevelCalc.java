package de.tobias.pokegame.backend.calc;

import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

public class LevelCalc {
	
	public static int getXpForLevel(int level) {
		return level * level * level;
	}
	
	public static double getXpPercentageForMonster(CurrentMonster monster) {
		double xpAfterLevel = monster.getXp() - LevelCalc.getXpForLevel(monster.getLevel());
		double xpToLevel = LevelCalc.getXpForLevel(monster.getLevel()+1) - (double) LevelCalc.getXpForLevel(monster.getLevel());
		double xpPercent = xpAfterLevel / xpToLevel;
		
		if (xpPercent >= 1.00 ) {
			monster.setLevel(monster.getLevel()+1); // raise monster level
			return getXpPercentageForMonster(monster); // call the method a second time because the calculations above need to be done again
		}
		return xpPercent;
	}
	
	public static int getXpFromBase(int base, int enemyLevel, boolean tamed) {
		double tameStat = tamed ? 1.5 : 1;
		return (int) ((base * enemyLevel *  tameStat) / 7);
	}
}
