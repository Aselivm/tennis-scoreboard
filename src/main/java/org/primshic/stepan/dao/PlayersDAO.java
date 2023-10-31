package org.primshic.stepan.dao;


import org.hibernate.Session;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class PlayersDAO extends BaseDAO implements CRUD<Players> {

    @Override
    public List<Players> index() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Players").getResultList();
        }
    }

    @Override
    public Optional<Players> show(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return Optional.of(session.get(Players.class,id));
        }
    }

    public List<Players> showByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Players where name = :name").setParameter("name",name).getResultList();
        }
    }
    @Override
    public void save(Players players) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(players);
            session.getTransaction().commit();
        }
    }

    @Override
    @Deprecated
    public void update(int id, Players players) {
    }

    @Override
    public void delete(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Players players = session.get(Players.class,id);
            session.delete(players);
            session.getTransaction().commit();
        }
    }
}
