package dk.stork.entities;

import dk.stork.exceptions.EntityNotFoundException;
import dk.stork.requestHandling.communicationObjects.PublicUserObject;
import dk.stork.requestHandling.communicationObjects.RegisterUserRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * @author Johannes Ernstsen
 */
public class EntityFactory {
    private static Session session;
    private static SessionFactory sessionFactory;

    private static void initializeSession() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static <T> T getModelObject(int id, Class<T> clazz) {
        if (session == null) {
            initializeSession();
        } else if (!session.isOpen()) {
            session = sessionFactory.openSession();
        }
        session.beginTransaction();

        T result = session.load(clazz, id);

        session.getTransaction().commit();
        return result;
    }

    public static <T extends EntityObject> List<T> getModelObjects(Class<T> clazz) {
        if (session == null) {
            initializeSession();
        } else if (!session.isOpen()) {
            session = sessionFactory.openSession();
        }
        session.beginTransaction();
        List<T> result = session.createQuery("from clazz".replace("clazz", clazz.getSimpleName()), clazz).list();
        session.getTransaction().commit();
        return result;

    }

    static void save(Object object) {
        if (session == null) {
            initializeSession();
        } else if (!session.isOpen()) {
            session = sessionFactory.openSession();
        }

        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println("Commit failed. Rolling back");
            session.getTransaction().rollback();
        }

    }


    public static void destroy() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    /**
     * retrieve user by the use of their email
     * <p>
     * CASE IS IGNORED
     *
     * @param email email to search for
     * @return the user associated with the email
     */
    public static User getUserFromEmail(String email) {
        List<User> users = getModelObjects(User.class);
        for (User user : users) {
            if (email.equalsIgnoreCase(user.getMail())) {
                return user;
            }
        }
        throw new EntityNotFoundException("No user found with email: " + email);
    }

    /**
     * @param sessionId the sessionId for the user to be found
     * @return the {@link User} with the given sessionId
     */
    public static User getUserFromSessionId(String sessionId) {
        if (sessionId != null && !sessionId.equals("")) {
            List<User> users = getModelObjects(User.class);
            for (User user : users) {
                if (sessionId.equals(user.getSessionId())) {
                    return user;
                }
            }
        }
        throw new EntityNotFoundException("No user found with session: " + sessionId);
    }

    /**
     * Creates a user in the database from a {@link RegisterUserRequest}
     *
     * @param req request with information for creating the user
     * @return the user
     */
    public static int userFromRegisterRequest(RegisterUserRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setMail(req.getMail());
        user.setPassword(req.getPassword());
        user.save();
        return user.getId();
    }

    /**
     * Returns list with users for each id
     *
     * @param ids ids of the users to retrieve
     * @return {@link List<User>}
     */
    public static List<User> getUsers(List<Integer> ids) {
        ArrayList<User> result = new ArrayList<>();
        List<User> users = getModelObjects(User.class);
        for (User user : users) {
            if (ids.contains(user.getId())) {
                result.add(user);
            }
        }
        return result;
    }

    public static List<Group> getGroups(List<Integer> ids) {
        ArrayList<Group> result = new ArrayList<>();
        List<Group> groups = getModelObjects(Group.class);
        for (Group gro : groups) {
            if (ids.contains(gro.getId())) {
                result.add(gro);
            }
        }
        return result;
    }

    /**
     * @return {@link List<PublicUserObject> with {@link PublicUserObject} representing all {@link User}s in the database}
     */
    public static List<PublicUserObject> getAllUsersAsPublicUserObjects() {
        List<User> users = getModelObjects(User.class);
        ArrayList<PublicUserObject> result = new ArrayList<>();
        for (User user : users) {
            result.add(new PublicUserObject(user.getId(), user.getName(), user.getMail()));
        }
        return result;
    }

    /**
     * @param id userId of the user to be logged in
     * @return the sessionId for the user
     */
    public static String login(int id) {
        User user = getModelObject(id, User.class);
        return login(user);
    }

    /**
     * @param user user to be logged in
     * @return the sessionId for the user
     */
    public static String login(User user) {
        String sessionId = UUID.randomUUID().toString();
        user.setSessionId(sessionId);
        user.save();
        return sessionId;
    }
}
