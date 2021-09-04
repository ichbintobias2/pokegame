package de.tobias.pokegame.backend.persistence;

import java.util.ArrayList;
import java.util.List;

import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

public class PlayerTeam {
	
	private List<CurrentMonster> monsters = new ArrayList<>();
	
	private final int teamSize = 4;
	
	public PlayerTeam() {
		monsters.addAll(Savegame.getPlayerTeam());
	}
	
	public List<CurrentMonster> list() {
		return monsters;
	}
	
	public void add(CurrentMonster monster) {
		if (monsters.size() < teamSize) {
			monsters.add(monster);
		}
	}
}
