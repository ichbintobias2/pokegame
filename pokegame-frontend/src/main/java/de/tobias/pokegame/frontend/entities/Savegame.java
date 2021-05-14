package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.entities.player.Gamestate;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import lombok.Getter;

public class Savegame {
	
	private static Gamestate gamestate;
	private static String currentLocation;
	
	@Getter
	private static List<CurrentMonster> playerTeam;
	
	/* Add those later as they become needed
	private String playerLocationX;
	private String playerLocationY;
	
	private String totalTimePlayed;
	private int totalSeen;
	private int totalCaught;
	private int earnedBadges; */
	
	static {
		gamestate = NitriteManager.loadGamestate();
		currentLocation = gamestate.getPlayerLocationMap();
		
		List<Long> teamIds = gamestate.getPlayerTeam();
		playerTeam = new ArrayList<CurrentMonster>();
		
		for (Long monsterId : teamIds) {
			CurrentMonster dbEntry = NitriteManager.getCurrentMonsterById(monsterId);
			playerTeam.add(dbEntry);
		}
	}
	
	/* public static void saveGame() {
		List<Long> teamIds = new ArrayList<Long>();
		
		for (Monster monster : playerTeam) {
			teamIds.add(monster.getn);
		}
		
		Gamestate newGamestate = new Gamestate(currentLocation, null, null, null, 0, 0, 0, teamIds);
		NitriteManager.saveGamestate(newGamestate);
	} */
}
