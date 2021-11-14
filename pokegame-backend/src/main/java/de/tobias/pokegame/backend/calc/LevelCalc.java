package de.tobias.pokegame.backend.calc;

public class LevelCalc {
	
	public static int getXpForLevel(int level) {
		return level * level * level;
	}
}
