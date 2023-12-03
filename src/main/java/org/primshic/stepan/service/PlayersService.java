package org.primshic.stepan.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.util.HibernateUtil;

import java.util.Optional;

public class PlayersService {
    public Optional<Players> getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Players WHERE name = :playerName";
            Query<Players> query = session.createQuery(hql, Players.class);
            query.setParameter("playerName", name);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    public Optional<Players> getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Players player = session.get(Players.class, id);
            return Optional.ofNullable(player);
        }
    }

    public Optional<Players> getEntity(String name) {
        Optional<Players> player = getByName(name);
        if (player.isEmpty()) {
            player = save(name);
        }
        return player;
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

            return Optional.of(players);
        }
    }

}
