package de.tobias.pokegame.frontend.menu;

import java.util.Random;

import de.gurkenlabs.litiengine.Game;
import de.tobias.pokegame.backend.entities.monster.BaseMonster;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.persistence.NitriteManager;
import de.tobias.pokegame.frontend.BattleControl;
import de.tobias.pokegame.frontend.ui.Dialog;

public class ItemMenu extends KeyboardMenu {
	
	private static final double x = Game.window().getResolution().getWidth() / 1.4;
	private static final double y = Game.window().getResolution().getHeight() / 3;
	
	private static final double width = 400;
	private static final double height = 400;
	
	private static ItemMenu instance;
	
	public ItemMenu() {
		super(x, y, width, height, "Throw Capsule", "1", "2"); // TODO texts, translations
		
		onConfirm(c -> {
			switch (c.intValue()) {
			case 0:
				throwCapsule();
				break;
			case 1:
				break;
			case 2:
				break;
			}
		});
	}
	
	public static ItemMenu instance() {
		if (instance == null) {
			instance = new ItemMenu();
		}
		
		return instance;
	}
	
	private void throwCapsule() {
		// disable instance because pressing enter would repeatedly call this method
		instance.setEnabled(false);
		
		Dialog.instance().clearQueue();
		Dialog.instance().addToQueue("Capsule thrown!");
		
		Dialog.instance().addToQueue("... 1");
		Dialog.instance().addToQueue("... 2");
		Dialog.instance().addToQueue("... 3");
		
		// adding different dialog depending on catch result
		if (determineCapsuleSuccess()) {
			Dialog.instance().addToQueue("Success!");
			Dialog.instance().addToQueue("[stop battle]");
			BattleControl.catchWild();
		} else {
			Dialog.instance().addToQueue("The monster escaped!");
		}
		
		Dialog.instance().setVisible(true);
		Dialog.instance().enable(true);
	}
	
	private boolean determineCapsuleSuccess() {
		// get the current wild monster and determine its catch rate
		CurrentMonster encounter = BattleControl.getEncounter();
		BaseMonster encounterBase = NitriteManager.getBaseMonsterByRegistryNr(encounter.getRegistryNumber());
		int encounterCatchRate = encounterBase.getCatchRate();
		
		// generate a random number and if it is smaller the catch is successful
		int randomNumber = new Random().nextInt(101);
		if (randomNumber < encounterCatchRate) {
			return true;
		}
		
		// return false if no catching condition applies successfully
		return false;
	}
}
