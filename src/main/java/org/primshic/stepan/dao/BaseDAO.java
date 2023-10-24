package org.primshic.stepan.dao;

import org.h2.command.ddl.CreateTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primshic.stepan.model.Match;

import java.sql.Statement;

public class BaseDAO {
    protected final SessionFactory sessionFactory;
    protected final Match match;

    public BaseDAO(Match match, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.match = match;
        init();
    }


    public void init(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.doWork((connection)->{
                String createPlayerTable = "CREATE TABLE Player" +
                        " (id INT PRIMARY KEY AUTO_INCREMENT" +
                        ", name VARCHAR(255))";
                String createMatchTable = "CREATE TABLE Match( " +
                        "id INT PRIMARY KEY AUTO_INCREMENT ," +
                        "player1_id int references Player(id) ," +
                        "player2_id int references Player(id), " +
                        "player_winner_id int references Player(id))";
                try (Statement statement = connection.createStatement()) {
                    statement.execute(createPlayerTable);
                    statement.execute(createMatchTable);
                }
            });

            session.persist(match);
            transaction.commit();
            Match match1 = session.get(Match.class,1);
            System.out.println(match1.getPlayer2());
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
