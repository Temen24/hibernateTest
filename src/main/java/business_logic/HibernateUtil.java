package business_logic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.ContactService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static Logger log = Logger.getLogger(ContactService.class.getName());

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            log.log(Level.SEVERE, "Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
