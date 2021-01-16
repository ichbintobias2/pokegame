package de.tobias.pokegame.backend.calc;

import java.util.List;

import de.tobias.pokegame.backend.entities.monster.Effective;
import de.tobias.pokegame.backend.entities.monster.Type;
import de.tobias.pokegame.backend.persistence.NitriteManager;

public class TypeCalc {
	private String attackAsString;
	private List<String> defenseTypings;
	
	public TypeCalc(String attackType, List<String> defenseTypings) {
		this.attackAsString = attackType;
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
		double eff1 = getEffectivenessAsDouble(attackAsString, defenseTypings.get(0));
		double eff2 = getEffectivenessAsDouble(attackAsString, defenseTypings.get(1));
		
		return eff1 * eff2;
	}
	
	private double getEffectivenessAsDouble(String attackType, String defenseType) {
		Type atkType = getTypeInfoFromString(attackType);
		Type defType = getTypeInfoFromString(defenseType);
		
		if (atkType.getDoubleDamageTo().contains(defenseType) && defType.getDoubleDamageFrom().contains(attackType)) {
			return Effective.TWICE;
		} else if (atkType.getNormalDamageTo().contains(defenseType) && defType.getNormalDamageFrom().contains(attackType)) {
			return Effective.NORMAL;
		} else if (atkType.getHalfDamageTo().contains(defenseType) && defType.getHalfDamageFrom().contains(attackType)) {
			return Effective.HALF;
		} else if (atkType.getNoDamageTo().contains(defenseType) &&  defType.getNoDamageFrom().contains(attackType)) {
			return Effective.ZERO;
		} else throw new RuntimeException("Could not determine effectiveness!");
	}
	
	private Type getTypeInfoFromString(String typeName) {
		return NitriteManager.getTypeByName(typeName);
	}
}
