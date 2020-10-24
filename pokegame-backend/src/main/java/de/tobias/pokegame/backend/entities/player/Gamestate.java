package de.tobias.pokegame.backend.entities.player;

import lombok.Data;

@Data
public class Gamestate {

	private String playerLocationMap;
	private String playerLocationX;
	private String playerLocationY;
	
	private String totalTimePlayed;
	private int totalSeen;
	private int totalCaught;
	private int earnedBadges;
}
