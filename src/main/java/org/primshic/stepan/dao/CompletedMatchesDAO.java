package org.primshic.stepan.dao;

import org.hibernate.Session;
import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;


public class CompletedMatchesDAO extends BaseDAO implements CRUD<Matches> {

    @Override
    public List<Matches> index() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matches").getResultList();
        }
    }

    public List<Matches> indexByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matches m " +
                            "WHERE :name IN (m.players1.name, m.players2.name, m.winner.name)", Matches.class)
                    .setParameter("name", name)
                    .getResultList();
        }
    }

    @Override
    public Optional<Matches> getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.of(session.get(Matches.class, id));
        }
    }

    @Override
    public Optional<Players> save(Matches matches) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(matches);
            session.getTransaction().commit();
        }
        return null;
    }

    @Override
    @Deprecated
    public void update(int id, Matches matches) {
    }

    @Override
    public void delete(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Matches matches = session.get(Matches.class, id);
            session.delete(matches);
            session.getTransaction().commit();
        }
    }
}
