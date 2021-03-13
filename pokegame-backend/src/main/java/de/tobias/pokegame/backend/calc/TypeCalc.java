package de.tobias.pokegame.backend.calc;

import java.util.List;

import de.tobias.pokegame.backend.entities.monster.Type;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class TypeCalc {
	private String attackName;
	private List<String> defenseTypings;
	
	public TypeCalc(String attackName, List<String> defenseTypings) {
		this.attackName = attackName;
		this.defenseTypings = defenseTypings;
	}

	public String getEffectivenessAsString() {
		double finalMultiplier = getFinalMultiplier();
		
		if (finalMultiplier == 2 || finalMultiplier == 4) {
			return "Es ist sehr effektiv!";
		} else if (finalMultiplier == 1) {
			return "Es verursachte normalen Schaden!";
		} else if (finalMultiplier == 0.5 || finalMultiplier == 0.25) {
			return "Es ist nicht sehr effektiv...";
		} else if (finalMultiplier == 0) {
			return "Es hatte keine Wirkung...";
		} else throw new RuntimeException("Could not determine effectiveness!");
	}
	
	private double getFinalMultiplier() {
		String attackType = NitriteManager.getAttackByName(attackName).getType();
		double eff1 = getEffectivenessAsDouble(attackType, defenseTypings.get(0));
		double eff2 = 1;
		
		if (defenseTypings.size() == 2) {
			eff2 = getEffectivenessAsDouble(attackType, defenseTypings.get(1));
		}
		
		return eff1 * eff2;
	}
	
	private double getEffectivenessAsDouble(String attackType, String defenseType) {
		Type atkType = NitriteManager.getTypeByName(attackType);
		Type defType = NitriteManager.getTypeByName(defenseType);
		
		if (atkType.getDoubleDamageTo().contains(defenseType) && defType.getDoubleDamageFrom().contains(attackType)) {
			return Effectiveness.TWICE;
		} else if (atkType.getHalfDamageTo().contains(defenseType) && defType.getHalfDamageFrom().contains(attackType)) {
			return Effectiveness.HALF;
		} else if (atkType.getNoDamageTo().contains(defenseType) &&  defType.getNoDamageFrom().contains(attackType)) {
			return Effectiveness.ZERO;
		} else return Effectiveness.NORMAL;
	}
}
