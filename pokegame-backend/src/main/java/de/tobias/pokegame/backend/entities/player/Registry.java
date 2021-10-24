package de.tobias.pokegame.backend.entities.player;

import java.util.List;

import lombok.Data;

@Data
public class Registry {
	
	private List<RegistryEntry> entries;
	
	public boolean checkSeen(int registryNr) {
		return entries.get(registryNr).isSeen();
	}
	
	public boolean checkCaught(int registryNr) {
		return entries.get(registryNr).isCaught();
	}
	
	public void setSeen(int registryNr) {
		entries.get(registryNr).setSeen(true);
	}
	
	public void setCaught(int registryNr) {
		entries.get(registryNr).setCaught(true);
	}
}
