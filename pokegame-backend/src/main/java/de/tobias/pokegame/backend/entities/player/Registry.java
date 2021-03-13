package de.tobias.pokegame.backend.entities.player;

import lombok.Data;

@Data
public class Registry {

	private String monsterId;
	private boolean seen;
	private boolean caught;
}
