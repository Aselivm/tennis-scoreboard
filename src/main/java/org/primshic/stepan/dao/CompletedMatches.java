package org.primshic.stepan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primshic.stepan.model.Matches;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;


public class CompletedMatches implements CrudDAO<Matches> {

    private SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public List<Matches> index() {
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Matches").getResultList();
        }
    }

    public List<Matches> pageIndex(int pageNumber) {
        int pageSize = 5;
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Matches", Matches.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }

    public List<Matches> pageIndexByName(int pageNumber, String name) {
        int pageSize = 5;
        try (Session session = sf.openSession()) {
            return session.createQuery("FROM Matches m WHERE :name IN (m.players1.name, m.players2.name, m.winner.name)", Matches.class)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .setParameter("name", name)
                    .getResultList();
        }
    }

    @Override
    public Optional<Matches> getById(int id) {
        try (Session session = sf.openSession()) {
            return Optional.of(session.get(Matches.class, id));
        }
    }

    public void save(Matches matches) {
        try (Session session = sf.openSession()) {
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
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Matches matches = session.get(Matches.class, id);
            session.delete(matches);
            session.getTransaction().commit();
        }
    }
}
