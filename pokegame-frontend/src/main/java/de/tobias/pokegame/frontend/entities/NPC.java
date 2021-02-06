package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.tobias.pokegame.backend.entities.npc.DbNPC;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.screens.Dialog;
import lombok.Getter;

@EntityInfo(width = 16, height = 16)
@AnimationInfo(spritePrefix = "player")
public class NPC extends Creature {
	@Getter private List<String> dialogLines = new ArrayList<String>();
	private int cooldown = 500;
	private long since = 0;
	
	public NPC() {
		// this.addController(new NpcController(this));
		
		String name = "npc1"; // TODO get this dynamically from game.litidata file
		DbNPC db = NitriteManager.getNpcByName(name);
		
		this.onMessage(e -> {
			getTalkedTo();
		});
		
		dialogLines.addAll(db.getDialogLines());
	}
	
	private void getTalkedTo() {
		if (Game.time().since(since) > cooldown) {
			Dialog.instance().addToQueue(dialogLines);
			Dialog.instance().setVisible(true);
			Dialog.instance().enable(true);
			GameLogic.setState(GameState.TALKING);
			since = Game.time().now();
		}
	}
}
