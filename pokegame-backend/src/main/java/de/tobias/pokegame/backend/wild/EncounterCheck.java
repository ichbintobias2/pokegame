package de.tobias.pokegame.backend.wild;

import java.util.Random;

import de.tobias.pokegame.backend.entities.location.LocationEncounterTable;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class EncounterCheck {

	public static boolean isEncountered() {
		int localEncounterPercentage = 50;
		
		return new Random().nextInt(101) > localEncounterPercentage;
	}
	
	public static int getEncounter(String locationName) {
		LocationEncounterTable let = NitriteManager.getLetByLocationName(locationName);
		
		int randomInt = new Random().nextInt(101);
		int percentageSum = 0;
		int counter = 0;
		
		while (percentageSum < randomInt) {
			percentageSum += let.getEncounterRates().get(counter);
			counter++;
		}
		
		return let.getBaseMonsterIds().get(counter-1);
	}
}
