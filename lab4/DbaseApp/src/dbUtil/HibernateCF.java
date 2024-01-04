package dbUtil;

//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;

import model.Program;

public class HibernateCF {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            config.addAnnotatedClass(Program.class);

            sessionFactory = config.buildSessionFactory();
        }
        return sessionFactory;
    }
}
