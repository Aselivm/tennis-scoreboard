package org.primshic.stepan.dao;


import org.hibernate.Session;
import org.primshic.stepan.entity.Player;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayerDAO extends BaseDAO implements CRUD<Player> {

    @Override
    public List<Player> index() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Player").getResultList();
        }
    }

    @Override
    public Optional<Player> show(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return Optional.of(session.get(Player.class,id));
        }
    }

    @Override
    public void save(Player player) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
    }

    @Override
    @Deprecated
    public void update(int id, Player player) {
    }

    @Override
    public void delete(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Player player = session.get(Player.class,id);
            session.delete(player);
            session.getTransaction().commit();
        }
    }
}
