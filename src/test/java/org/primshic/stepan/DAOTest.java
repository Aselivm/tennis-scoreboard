package org.primshic.stepan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.primshic.stepan.entity.Match;
import org.primshic.stepan.entity.Player;
import org.primshic.stepan.util.HibernateUtil;

public class DAOTest {

    @Test
    public void dao(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Player player1 = new Player("Steve");
        Player player2 = new Player("Tom");
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(match);
            transaction.commit();
        }
    }
}
