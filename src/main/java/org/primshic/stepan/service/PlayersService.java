package org.primshic.stepan.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.util.HibernateUtil;

import java.util.Optional;

public class PlayersService {
    public Optional<Players> showByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Players WHERE name = :playerName";
            Query<Players> query = session.createQuery(hql, Players.class);
            query.setParameter("playerName", name);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    public Optional<Players> saveOrGet(String name) {
        Optional<Players> player2 = showByName(name);
        if (player2.isEmpty()) {
            player2 = save(name);
        }
        return player2;
    }

    public Optional<Players> save(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            Players players = null;

            try {
                transaction = session.beginTransaction();

                players = new Players(name);
                session.persist(players);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e; // rethrow the exception after rollback
            }

            return Optional.ofNullable(players);
        }
    }
}
