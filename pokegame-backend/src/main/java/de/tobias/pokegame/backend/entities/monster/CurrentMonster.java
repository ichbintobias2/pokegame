package de.tobias.pokegame.backend.entities.monster;

import java.util.ArrayList;
import java.util.List;

import org.dizitart.no2.objects.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentMonster {
	
	@Id
	private long id;
	
	private int registryNumber;
	private String name;
	
	// TODO should not be in here but is currently needed
	private List<String> types = new ArrayList<String>();
	
	private int level;
	
	private String nature;
	
	private List<String> attacks = new ArrayList<String>();
	
	private String heldItem;
	
	private String foundAt;
	private int foundWithLv;
	private String originalTrainer;
	
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
