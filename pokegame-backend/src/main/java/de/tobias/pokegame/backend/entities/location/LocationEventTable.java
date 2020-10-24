package de.tobias.pokegame.backend.entities.location;

import lombok.Data;

@Data
public class LocationEventTable {

	private String locationName;
	private String eventName;
	private boolean eventHappened;
}
