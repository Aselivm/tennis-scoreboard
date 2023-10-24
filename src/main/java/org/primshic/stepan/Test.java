package org.primshic.stepan;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.primshic.stepan.dao.BaseDAO;
import org.primshic.stepan.dao.MatchDAO;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.model.Player;

public class Test {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Player.class).addAnnotatedClass(Match.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Player player1 = new Player(1,"Steve");
        Player player2 = new Player(2,"Tom");
        Match match = new Match(player1,player2);
        BaseDAO baseDAO = new BaseDAO(match,sessionFactory);
    }
}
