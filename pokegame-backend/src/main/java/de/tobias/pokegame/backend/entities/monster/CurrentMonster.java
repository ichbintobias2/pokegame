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
	
	private int currentHp = 145;
	
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
	
	// TODO placeholders
	private String attack1 = "Attack 1";
	private String attack2 = "Attack 2";
	private String attack3 = "Attack 3";
	private String attack4 = "Attack 4";
}
