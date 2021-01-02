package de.tobias.pokegame.frontend.entities;

import java.util.ArrayList;
import java.util.List;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.tobias.pokegame.frontend.GameLogic;
import de.tobias.pokegame.frontend.enums.GameState;
import de.tobias.pokegame.frontend.screens.Dialog;

@EntityInfo(width = 16, height = 16)
@AnimationInfo(spritePrefix = "player")
public class NPC extends Creature {
	private List<String> dialogLines = new ArrayList<String>(); // will be obtained from database later on
	private int cooldown = 500;
	private long since = 0;
	
	public NPC() {
		// this.addController(new NpcController(this));

		this.onMessage(e -> {
			getTalkedTo();
		});
		
		// TODO these will be a substitute until actual values are in the database
		dialogLines.add("Hello");
		dialogLines.add("Hi");
		dialogLines.add("[battle]");
		// TODO dialogLines.add("bye");
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
	
	public List<String> getDialogLines() {
		return dialogLines;
	}
}
