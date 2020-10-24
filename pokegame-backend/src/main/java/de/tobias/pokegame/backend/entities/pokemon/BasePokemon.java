package de.tobias.pokegame.backend.entities.pokemon;

import java.util.List;

import lombok.Data;

@Data
public class BasePokemon {

	private String name;
	private int dexNumber;
	
	private int baseHp;
	private int baseAttack;
	private int baseDefense;
	private int baseSpAtk;
	private int baseSpDef;
	private int baseSpeed;
	
	private List<String> locations;
	
	private List<String> learnableMoves;
	
	private String givesEv;
}
