package org.primshic.stepan.dao;

import org.hibernate.Session;
import org.primshic.stepan.entity.Matches;
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

    public List<Matches> pageIndex(int pageNumber) {
        int pageSize = 5;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matches", Matches.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public List<Matches> pageIndexByName(int pageNumber, String name) {
        int pageSize = 5;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Matches m WHERE :name IN (m.players1.name, m.players2.name, m.winner.name)", Matches.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
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

    public void save(Matches matches) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(matches);
            session.getTransaction().commit();
        }
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
