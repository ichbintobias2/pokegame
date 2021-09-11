package de.tobias.pokegame.backend.entities.monster;

import org.dizitart.no2.objects.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonsterBoxListItem {
	
	@Id
	private long id;
	private long currentMonsterId;
	private int coordX;
	private int coordY;
}
