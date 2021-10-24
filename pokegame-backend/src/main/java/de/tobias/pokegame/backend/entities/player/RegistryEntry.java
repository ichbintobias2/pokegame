package de.tobias.pokegame.backend.entities.player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistryEntry {
	
	private int monsterId;
	private boolean seen;
	private boolean caught;
}
