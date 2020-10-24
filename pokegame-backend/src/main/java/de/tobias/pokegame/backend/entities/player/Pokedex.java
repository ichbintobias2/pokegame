package de.tobias.pokegame.backend.entities.player;

import lombok.Data;

@Data
public class Pokedex {

	private String pokemonId;
	private boolean seen;
	private boolean caught;
}
