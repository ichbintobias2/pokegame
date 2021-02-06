package de.tobias.pokegame.backend.entities.npc;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DbNPC {

	private String name;
	private boolean wantsToBattle;
	private List<String> team = new ArrayList<String>();
	private List<String> dialogLines = new ArrayList<String>();
}
