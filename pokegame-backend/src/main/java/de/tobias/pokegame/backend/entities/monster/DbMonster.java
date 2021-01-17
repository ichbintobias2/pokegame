package de.tobias.pokegame.backend.entities.monster;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DbMonster {
	
	private String name;
	private int registryNumber;
	
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
	
	private String nickname;
	
	private String foundAt;
	private int foundWithLv;
	private String originalTrainer;
	
	private List<String> attacks = new ArrayList<String>();
	
	private String heldItem;
	
	private int dvHp;
	private int dvAttack;
	private int dvDefense;
	private int dvSpAtk;
	private int dvSpDef;
	private int dvSpeed;
	
	private int evHp;
	private int evAttack;
	private int evDefense;
	private int evSpAtk;
	private int evSpDef;
	private int evSpeed;
}
