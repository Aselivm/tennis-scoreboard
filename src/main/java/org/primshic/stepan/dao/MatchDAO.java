package org.primshic.stepan.dao;

import org.hibernate.SessionFactory;
import org.primshic.stepan.entity.Match;

import javax.transaction.Transactional;

@Transactional
public class MatchDAO extends BaseDAO{
    public MatchDAO(Match match, SessionFactory sessionFactory) {
        super(match,sessionFactory);
    }
}
