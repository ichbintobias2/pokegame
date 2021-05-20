package de.tobias.pokegame.backend.entities.location;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class LocationEncounterTable {
	
	private String locationName;
	private List<Integer> baseMonsterIds = new ArrayList<Integer>();
	private List<Integer> encounterRates = new ArrayList<Integer>();
}
