package de.tobias.pokegame.frontend.entities;

import java.util.Random;

public class EnemyMonsterController {
	private static EnemyMonsterController instance;
	
	public static EnemyMonsterController instance() {
		if (instance == null) {
			instance = new EnemyMonsterController();
		}
		
		return instance;
	}
	
	public String decideEnemyAttack() {
		if (doesSwitchMakeSense()) {
			// switch the monster out
		} else {
			if (isFasterThanPlayer()) {
				if (isInKillingRange()) {
					return useKillingAttack();
				} else if (boostNeeded() && !expectingLethal()) {
					return useAdequateBoostMove();
				} else {
					 return useRandomAttack();
				}
			} else if (!isFasterThanPlayer()) {
				if (expectingLethal()) {
					if (hasPriorityMove()) {
						return useAdequatePriorityMove();
					} else {
						return useBestAttack();
					}
				} else {
					return useBestAttack();
				}
			}
		}
		
		return "";
	}

	private boolean doesSwitchMakeSense() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean isFasterThanPlayer() {
		int ownSpeed = EnemyMonster.instance().getData().getCurrentSpeed();
		int playerSpeed = PlayerMonster.instance().getData().getCurrentSpeed();
		
		if (ownSpeed > playerSpeed) {
			return true;
		} else if (ownSpeed == playerSpeed) {
			int random = new Random().nextInt(2);
			return random > 0;
		} else return false;
	}

	private boolean isInKillingRange() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean boostNeeded() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean expectingLethal() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean hasPriorityMove() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private String useKillingAttack() {
		// TODO Auto-generated method stub
		return null;
	}

	private String useBestAttack() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String useAdequateBoostMove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String useAdequatePriorityMove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String useRandomAttack() {
		int randomSlot = new Random().nextInt(4);
		return EnemyMonster.instance().getAttack(randomSlot);
	}
}
