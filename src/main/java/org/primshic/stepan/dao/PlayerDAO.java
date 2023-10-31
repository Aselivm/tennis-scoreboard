package org.primshic.stepan.dao;


import org.hibernate.SessionFactory;
import org.primshic.stepan.entity.Match;

public class PlayerDAO extends BaseDAO {
    public PlayerDAO(Match match, SessionFactory sessionFactory) {
        super(match,sessionFactory);
    }

}
