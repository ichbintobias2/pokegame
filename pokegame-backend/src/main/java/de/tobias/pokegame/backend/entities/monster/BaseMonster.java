package de.tobias.pokegame.backend.entities.monster;

import java.util.List;

import lombok.Data;

@Data
public class BaseMonster {

	private String name;
	private int registryNumber;
	
	private List<String> types;
	
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
