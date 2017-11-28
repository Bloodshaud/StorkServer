package dk.stork.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class EntityFactory {
    private static Session session;


    private static void initializeSession() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static <T> T getModelObject(Class<T> clazz, int id) {
        if (session == null || !session.isOpen()) {
            initializeSession();
        }
        session.beginTransaction();

        T result = session.load(clazz, id);

        session.getTransaction().commit();
        return result;
    }

    public static <T extends EntityObject> List<T> getModelObjects(Class<T> clazz) {
        if (session == null || !session.isOpen()) {
            initializeSession();
        }
        session.beginTransaction();
        return session.createQuery("from clazz".replace("clazz", clazz.getSimpleName()), clazz).list();

    }

    public static void save(Object object) {
        if (session == null || !session.isOpen()) {
            initializeSession();
        }
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();

    }


    public static void destroy() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

}
