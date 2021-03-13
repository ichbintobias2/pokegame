package de.tobias.pokegame.backend.entities.location;

import java.util.List;

import lombok.Data;

@Data
public class LocationEncounterTable {

	private String locationName;
	private String baseMonsterId;
	private int encounterRate;
}
