package de.tobias.pokegame.backend.persistence;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.dizitart.no2.tool.Exporter;
import org.dizitart.no2.tool.Importer;

import de.tobias.pokegame.backend.entities.monster.Attack;
import de.tobias.pokegame.backend.entities.monster.BaseMonster;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.entities.monster.Nature;
import de.tobias.pokegame.backend.entities.monster.Type;
import de.tobias.pokegame.backend.entities.npc.DbNPC;

public class NitriteManager {
	
	private static Nitrite db;
	private static ObjectRepository<BaseMonster> baseMonsterRepo;
	private static ObjectRepository<CurrentMonster> currentMonsterRepo;
	private static ObjectRepository<Type> typeRepo;
	private static ObjectRepository<Attack> attackRepo;
	private static ObjectRepository<Nature> natureRepo;
	private static ObjectRepository<DbNPC> npcRepo;
	
	private static String dbPath = "../pokegame-backend/src/main/resources/nitrite.db";
	private static String filepath = "../pokegame-backend/src/main/resources/export.json";
	
	private static String dbUser = "PokeUser";
	private static String dbPassword = "PokePW";
	
	public static void init() {
		db = Nitrite.builder()
			    .compressed()
			    .filePath(dbPath)
			    .openOrCreate(dbUser, dbPassword);
		
		baseMonsterRepo = db.getRepository(BaseMonster.class);
		currentMonsterRepo = db.getRepository(CurrentMonster.class);
		typeRepo = db.getRepository(Type.class);
		attackRepo = db.getRepository(Attack.class);
		natureRepo = db.getRepository(Nature.class);
		npcRepo = db.getRepository(DbNPC.class);
	}
	
	public static void closeDb() {
		db.commit();
		db.close();
	}
	
	public static void importDb() {
		Importer importer = Importer.of(db);
		importer.importFrom(filepath);
	}
	
	public static void exportDb() {
		Exporter exporter = Exporter.of(db);
		exporter.exportTo(filepath);
	}
	
	public static BaseMonster getBaseMonsterByName(String name) {
		return baseMonsterRepo.find(ObjectFilters.eq("name", name)).firstOrDefault();
	}
	
	public static BaseMonster getBaseMonsterByRegistryNr(int nr) {
		return baseMonsterRepo.find(ObjectFilters.eq("registryNumber", nr)).firstOrDefault();
	}
	
	public static void saveDbMonster(BaseMonster monster) {
		baseMonsterRepo.insert(monster);
	}
	
	public static CurrentMonster getCurrentMonsterByName(String name) {
		return currentMonsterRepo.find(ObjectFilters.eq("name", name)).firstOrDefault();
	}
	
	public static void saveCurrentMonster(CurrentMonster monster) {
		currentMonsterRepo.insert(monster);
	}
	
	public static Type getTypeByName(String name) {
		return typeRepo.find(ObjectFilters.eq("name", name)).firstOrDefault();
	}
	
	public static void saveType(Type type) {
		typeRepo.insert(type);
	}
	
	public static Attack getAttackByName(String name) {
		return attackRepo.find(ObjectFilters.eq("name", name)).firstOrDefault();
	}
	
	public static void saveAttack(Attack attack) {
		attackRepo.insert(attack);
	}
	
	public static Nature getNatureByName(String name) {
		return natureRepo.find(ObjectFilters.eq("name", name)).firstOrDefault();
	}
	
	public static void saveNature(Nature nature) {
		natureRepo.insert(nature);
	}
	
	public static DbNPC getNpcByName(String name) {
		return npcRepo.find(ObjectFilters.eq("name", name)).firstOrDefault();
	}
	
	public static void saveNpc(DbNPC npc) {
		npcRepo.insert(npc);
	}
}