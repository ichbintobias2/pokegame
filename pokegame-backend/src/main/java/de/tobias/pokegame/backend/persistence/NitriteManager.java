package de.tobias.pokegame.backend.persistence;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.dizitart.no2.tool.Exporter;
import org.dizitart.no2.tool.Importer;

import de.tobias.pokegame.backend.entities.location.LocationEncounterTable;
import de.tobias.pokegame.backend.entities.monster.Attack;
import de.tobias.pokegame.backend.entities.monster.BaseMonster;
import de.tobias.pokegame.backend.entities.monster.CurrentMonster;
import de.tobias.pokegame.backend.entities.monster.MonsterBox;
import de.tobias.pokegame.backend.entities.monster.Nature;
import de.tobias.pokegame.backend.entities.monster.Type;
import de.tobias.pokegame.backend.entities.npc.DbNPC;
import de.tobias.pokegame.backend.entities.player.Gamestate;
import de.tobias.pokegame.backend.entities.player.Registry;

public class NitriteManager {
	
	private static Nitrite db;
	private static ObjectRepository<BaseMonster> baseMonsterRepo;
	private static ObjectRepository<CurrentMonster> currentMonsterRepo;
	private static ObjectRepository<Type> typeRepo;
	private static ObjectRepository<Attack> attackRepo;
	private static ObjectRepository<Nature> natureRepo;
	private static ObjectRepository<DbNPC> npcRepo;
	private static ObjectRepository<LocationEncounterTable> letRepo;
	private static ObjectRepository<Gamestate> gsRepo;
	private static ObjectRepository<MonsterBox> boxRepo;
	private static ObjectRepository<Registry> registryRepo;
	
	private static String dbPath = "../pokegame-backend/src/main/resources/nitrite.db";
	private static String filepath = "../pokegame-backend/src/main/resources/export.json";
	
	private static String dbUser = "PokeUser";
	private static String dbPassword = "PokePW";
	
	public static void init() {
		db = Nitrite.builder()
				.compressed()
				.filePath(dbPath)
				.openOrCreate(dbUser, dbPassword);
		
		importDb();
		
		baseMonsterRepo = db.getRepository(BaseMonster.class);
		currentMonsterRepo = db.getRepository(CurrentMonster.class);
		typeRepo = db.getRepository(Type.class);
		attackRepo = db.getRepository(Attack.class);
		natureRepo = db.getRepository(Nature.class);
		npcRepo = db.getRepository(DbNPC.class);
		letRepo = db.getRepository(LocationEncounterTable.class);
		gsRepo = db.getRepository(Gamestate.class);
		boxRepo = db.getRepository(MonsterBox.class);
		registryRepo = db.getRepository(Registry.class);
	}
	
	public static void closeDb() {
		db.commit();
		db.close();
	}
	
	public static void importDb() {
		if (db.listRepositories().isEmpty()) {
			Importer.of(db).importFrom(filepath);
		}
	}
	
	public static void exportDb() {
		Exporter.of(db).exportTo(filepath);
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
	
	public static CurrentMonster getCurrentMonsterById(long id) {
		return currentMonsterRepo.find(ObjectFilters.eq("_id", id)).firstOrDefault();
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
	
	public static LocationEncounterTable getLetByLocationName(String locationName) {
		return letRepo.find(ObjectFilters.eq("locationName", locationName)).firstOrDefault();
	}
	
	public static void saveLet(LocationEncounterTable let) {
		letRepo.insert(let);
	}
	
	public static Gamestate loadGamestate() {
		return gsRepo.find().firstOrDefault();
	}
	
	public static void saveGamestate(Gamestate gs) {
		gsRepo.insert(gs);
	}
	
	public static MonsterBox getMonsterBox(long boxNr) {
		return boxRepo.find(ObjectFilters.eq("_id", boxNr)).firstOrDefault();
	}
	
	public static void saveMonsterBox(MonsterBox box) {
		boxRepo.insert(box);
	}
	
	public static Registry getRegistry() {
		return registryRepo.find(ObjectFilters.eq("_id", 1039779788361L)).firstOrDefault();
	}
	
	public static void saveRegistry(Registry registry) {
		registryRepo.insert(registry);
	}
}
