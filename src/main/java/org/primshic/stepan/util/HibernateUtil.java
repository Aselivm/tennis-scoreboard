package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.entity.Players;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        final Configuration configuration = new Configuration()
                .addAnnotatedClass(Players.class)
                .addAnnotatedClass(Matches.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
