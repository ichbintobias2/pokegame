package de.tobias.pokegame.backend.entities.npc;

import java.util.List;

import lombok.Data;

@Data
public class NPC {

	private String text;
	private boolean wantsToBattle;
	
	private List<String> team;
}
