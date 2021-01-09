package de.tobias.pokegame.backend.entities.monster;

import lombok.Data;

@Data
public class Attack {

	private String name;
	private int baseDamage;
	private String type;
	private int priority;
}
