package de.tobias.pokegame.backend.entities.monster;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Type {
	
	private String name;
	private List<String> doubleDamageTo = new ArrayList<String>();
	private List<String> halfDamageTo = new ArrayList<String>();
	private List<String> noDamageTo = new ArrayList<String>();
	
	private List<String> doubleDamageFrom = new ArrayList<String>();
	private List<String> halfDamageFrom = new ArrayList<String>();
	private List<String> noDamageFrom = new ArrayList<String>();
}
