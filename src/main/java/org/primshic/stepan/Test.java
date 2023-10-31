package org.primshic.stepan;

import org.hibernate.SessionFactory;
import org.primshic.stepan.entity.Match;
import org.primshic.stepan.entity.Player;
import org.primshic.stepan.util.HibernateUtil;

public class Test {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Player player1 = new Player("Steve");
        Player player2 = new Player("Tom");
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
    }
}
