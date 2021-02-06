package de.tobias.pokegame.backend.entities.monster;

import lombok.Data;

@Data
public class Nature {

	private String name;
	private double attack;
	private double defense;
	private double spAtk;
	private double spDef;
	private double speed;
}
