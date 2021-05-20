package de.tobias.pokegame.backend.entities.location;

import lombok.Data;

@Data
public class LocationItemTable {
	
	private String locationName;
	private String itemId;
	private boolean itemFound;
}
