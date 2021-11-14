package de.tobias.pokegame.backend.calc.test;

import de.tobias.pokegame.backend.calc.LevelCalc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LevelCalcTest {
	
	@Test
	public void getXpFromBaseTamedTrue() {
		int expected = 357;
		int actual = LevelCalc.getXpFromBase(50, 50, false);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void getXpFromBaseUntamedTrue() {
		int expected = 535;
		int actual = LevelCalc.getXpFromBase(50, 50, true);
		Assertions.assertEquals(expected, actual);
	}
}
