package de.tobias.pokegame.backend.entities.monster;

import java.util.List;

import lombok.Data;

@Data
public class Type {

	private String name;
	private List<String> normalDamageTo;
	private List<String> doubleDamageTo;
	private List<String> halfDamageTo;
	private List<String> noDamageTo;
	
	private List<String> normalDamageFrom;
	private List<String> doubleDamageFrom;
	private List<String> halfDamageFrom;
	private List<String> noDamageFrom;
}
