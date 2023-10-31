package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.primshic.stepan.entity.Match;
import org.primshic.stepan.entity.Player;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        final Configuration configuration = new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
