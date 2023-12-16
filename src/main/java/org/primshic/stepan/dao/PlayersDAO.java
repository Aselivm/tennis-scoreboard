package org.primshic.stepan.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayersDAO extends BaseDAO implements CRUD<Players> {

    @Override
    public List<Players> index() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Players").getResultList();
        }
    }

    @Override
    public Optional<Players> getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.of(session.get(Players.class, id));
        }
    }

    public List<Players> indexByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Players where name = :name").setParameter("name", name).getResultList();
        }
    }

    public Optional<Players> getByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Players WHERE name = :playerName";
            Query<Players> query = session.createQuery(hql, Players.class);
            query.setParameter("playerName", name);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    @Override
    public Optional<Players> save(Players savedPlayer) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(savedPlayer);
            transaction.commit();
            return Optional.of(savedPlayer);
        }
    }

    @Override
    @Deprecated
    public void update(int id, Players players) {
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Players players = session.get(Players.class, id);
            session.delete(players);
            session.getTransaction().commit();
        }
    }
}
