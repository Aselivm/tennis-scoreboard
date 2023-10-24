package org.primshic.stepan.dao;


import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primshic.stepan.model.Match;

public class PlayerDAO extends BaseDAO {
    public PlayerDAO(Match match, SessionFactory sessionFactory) {
        super(match,sessionFactory);
    }

}
