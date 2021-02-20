package de.tobias.pokegame.backend.entities.monster;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BaseMonster {
	
	private int registryNumber;
	private String name;
	
	private List<String> types = new ArrayList<String>();
	
	private int baseHp;
	private int baseAttack;
	private int baseDefense;
	private int baseSpAtk;
	private int baseSpDef;
	private int baseSpeed;
	
	private List<String> locations = new ArrayList<String>();
	
	private List<String> learnableMoves = new ArrayList<String>();
	
	private String givesEv;
}
