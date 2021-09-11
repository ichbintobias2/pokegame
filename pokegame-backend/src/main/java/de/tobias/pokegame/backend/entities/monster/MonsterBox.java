package de.tobias.pokegame.backend.entities.monster;

import java.util.List;

import org.dizitart.no2.objects.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonsterBox {
	
	@Id
	private long id;
	private List<MonsterBoxListItem> entries;
}
