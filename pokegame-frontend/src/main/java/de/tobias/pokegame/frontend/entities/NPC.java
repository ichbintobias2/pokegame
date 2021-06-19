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
import de.tobias.pokegame.frontend.constants.GameState;
import de.tobias.pokegame.frontend.ui.Dialog;

@EntityInfo(width = 16, height = 16)
@AnimationInfo(spritePrefix = { "player", "npc_placeholder" })
public class NPC extends Creature {
	
	private List<String> dialogLines = new ArrayList<String>();
	private int cooldown = 500;
	private long since = 0;
	private boolean once = false;
	
	public NPC() {
		// TODO this is a very messy implementation. It works but should be improved in the future
		// the onRendered method is called continuously and thus will waste resources
		onRendered(l -> {
			if (!once) {
				String name = this.getName();
				DbNPC db = NitriteManager.getNpcByName(name);
				dialogLines.addAll(db.getDialogLines());
				once = true;
			}
		});
		
		this.onMessage(e -> {
			getTalkedTo();
		});
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
