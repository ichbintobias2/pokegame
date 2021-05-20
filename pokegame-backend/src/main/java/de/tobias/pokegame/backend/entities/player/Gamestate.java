package de.tobias.pokegame.backend.entities.player;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gamestate {
	
	private String playerLocationMap;
	private String playerLocationX;
	private String playerLocationY;
	
	private String totalTimePlayed;
	private int totalSeen;
	private int totalCaught;
	private int earnedBadges;
	
	private List<Long> playerTeam = new ArrayList<Long>();
}
