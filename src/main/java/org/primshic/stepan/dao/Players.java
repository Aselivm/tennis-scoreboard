package org.primshic.stepan.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class Players implements CrudDAO<org.primshic.stepan.model.Players> {
    private SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public List<org.primshic.stepan.model.Players> index() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Players").getResultList();
        }
    }

    @Override
    public Optional<org.primshic.stepan.model.Players> getById(int id) {
        try (Session session = sf.openSession()) {
            return Optional.of(session.get(org.primshic.stepan.model.Players.class, id));
        }
    }

    @Override
    public void save(org.primshic.stepan.model.Players players) {

    }


    public Optional<org.primshic.stepan.model.Players> getByName(String name) {
        try (Session session = sf.openSession()) {
            String hql = "FROM Players WHERE name = :playerName";
            Query<org.primshic.stepan.model.Players> query = session.createQuery(hql, org.primshic.stepan.model.Players.class);
            query.setParameter("playerName", name);
            return Optional.ofNullable(query.uniqueResult());
        }
    }

    public Optional<org.primshic.stepan.model.Players> saveAndReturn(org.primshic.stepan.model.Players savedPlayer) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(savedPlayer);
            transaction.commit();
            return Optional.of(savedPlayer);
        }
    }

    @Override
    @Deprecated
    public void update(int id, org.primshic.stepan.model.Players players) {
    }

    @Override
    public void delete(int id) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            org.primshic.stepan.model.Players players = session.get(org.primshic.stepan.model.Players.class, id);
            session.delete(players);
            session.getTransaction().commit();
        }
    }
}
