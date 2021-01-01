package de.tobias.pokegame.frontend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attack {

	private int priority;	
	private int damage;
	private String name;
}
