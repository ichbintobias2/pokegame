package de.tobias.pokegame.backend.persistence;

import java.util.ArrayList;
import java.util.List;

import de.tobias.pokegame.backend.entities.monster.CurrentMonster;

public class PlayerTeam {
	
	private final List<CurrentMonster> monsters = new ArrayList<>();
	
	private static final int MAX_TEAM_SIZE = 4;
	
	public PlayerTeam() {
		monsters.addAll(Savegame.getPlayerTeam());
	}
	
	public List<CurrentMonster> list() {
		return monsters;
	}
	
	public void add(CurrentMonster monster) {
		if (monsters.size() < MAX_TEAM_SIZE) {
			monsters.add(monster);
		}
	}
}
