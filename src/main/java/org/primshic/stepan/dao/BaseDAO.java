package org.primshic.stepan.dao;

import org.hibernate.SessionFactory;

public class BaseDAO {
    protected final SessionFactory sessionFactory;

    public BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
