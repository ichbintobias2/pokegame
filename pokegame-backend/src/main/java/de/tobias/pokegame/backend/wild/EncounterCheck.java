package de.tobias.pokegame.backend.wild;

import java.util.Random;

import de.tobias.pokegame.backend.entities.location.LocationEncounterTable;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class EncounterCheck {
	
	/**
	 * Returns the registry number of the encountered monster (if any),
	 * @param locationName current location of the player
	 * @return registry id of encountered monster or null if no encounter happens
	 */
	public static Integer getEncounter(String locationName) {
		LocationEncounterTable let = NitriteManager.getLetByLocationName(locationName);
		
		int randomInt = new Random().nextInt(101);
		int counter = 0;
		
		for (Integer encounterRate : let.getEncounterRates()) {
			if (randomInt > encounterRate) {
				counter++;
			} else {
				return let.getBaseMonsterIds().get(counter);
			}
		}
		
		return null;
	}
}
