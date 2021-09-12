package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
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
public class NPC extends Creature implements IUpdateable {
	
	private List<List<String>> dialogLines = new ArrayList<List<String>>();
	private int dialogCooldown = 500;
	private long lastDialog = 0;
	private boolean initialized = false;
	private boolean wantsToBattle = true;
	private double tolerance = 1.0;
	
	public NPC() {
		this.onMessage(e -> {
			getTalkedTo();
		});
	}
	
	@Override
	public void update() {
		if (!initialized) {
			String name = this.getName();
			DbNPC db = NitriteManager.getNpcByName(name);
			wantsToBattle = db.isWantsToBattle();
			dialogLines.addAll(db.getDialogLines());
			initialized = true;
		}
		
		if (wantsToBattle) {
			checkForPlayer();
		}
	}
	
	private void getTalkedTo() {
		if (Game.time().since(lastDialog) > dialogCooldown) {
			Dialog.instance().setDialogPartner(this);
			Dialog.instance().addToQueue(dialogLines.get(0));
			Dialog.instance().setVisible(true);
			Dialog.instance().enable(true);
			GameLogic.setState(GameState.TALKING);
			lastDialog = Game.time().now();
		}
	}
	
	private void checkForPlayer() {
		// check if the player has roughly the same x or y coordinate
		if ((this.getX() + tolerance >= Player.instance().getX() && this.getX() - tolerance <= Player.instance().getX()) ||
				(this.getY() + tolerance >= Player.instance().getY() && this.getY() - tolerance <= Player.instance().getY())) {
			// disabling the method after this execution because it will create a loop otherwise
			wantsToBattle = false;
			getTalkedTo();
		}
	}
	
	public List<String> getDialogLines(int dialog) {
		return dialogLines.get(dialog);
	}
}
