package de.tobias.pokegame.backend.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.flywaydb.core.Flyway;

public class PersistenceManager {
	private static EntityManager em;

	public static void init() {
		Flyway.configure().baselineOnMigrate(true).dataSource("jdbc:h2:tcp://localhost/~/test", "sa", "").load().migrate();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	public static void save(Object entity) {
		em.persist(entity);
	}
	
	public static <T> T get(Class<T> e, long id) {
		return em.find(e, id);
	}
	
	public static void closeManager() {
		em.getTransaction().commit();
		em.close();
	}
}
