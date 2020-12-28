package de.tobias.pokegame.backend.entities.monster;

import java.util.List;

import lombok.Data;

@Data
public class CurrentMonster extends BaseMonster {

	private String nickname;
	
	private String foundAt;
	private int foundWithLv;
	private String originalTrainer;
	
	private List<String> moves;
	
	private int currentHp;
	
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
