package org.primshic.stepan.dao;

import org.hibernate.Session;
import org.primshic.stepan.entity.Match;
import org.primshic.stepan.util.HibernateUtil;

import java.util.List;
import java.util.Optional;


public class MatchDAO extends BaseDAO implements CRUD<Match>{

    @Override
    public List<Match> index(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Match").getResultList();
        }
    }

    @Override
    public Optional<Match> show(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return Optional.of(session.get(Match.class,id));
        }
    }

    @Override
    public void save(Match match){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(match);
            session.getTransaction().commit();
        }
    }

    @Override
    @Deprecated
    public void update(int id, Match match){
    }

    @Override
    public void delete(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Match match = session.get(Match.class,id);
            session.delete(match);
            session.getTransaction().commit();
        }
    }
}
