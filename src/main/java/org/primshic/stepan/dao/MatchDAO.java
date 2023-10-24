package org.primshic.stepan.dao;

import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.primshic.stepan.model.Match;

@Transactional
public class MatchDAO extends BaseDAO{
    public MatchDAO(Match match, SessionFactory sessionFactory) {
        super(match,sessionFactory);
    }
}
