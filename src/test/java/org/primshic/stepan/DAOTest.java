package org.primshic.stepan;

import org.junit.Assert;
import org.junit.Test;
import org.primshic.stepan.dao.MatchDAO;
import org.primshic.stepan.dao.PlayerDAO;
import org.primshic.stepan.entity.Match;
import org.primshic.stepan.entity.Player;

public class DAOTest {


    @Test
    public void dao(){//TODO make test order independent
        Player player1 = new Player("Steve");
        Player player2 = new Player("Tom");
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        MatchDAO matchDAO = new MatchDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        matchDAO.save(match);
        matchDAO.index();

        playerDAO.show(1);
        playerDAO.show(2);

        matchDAO.delete(1);
        Assert.assertThrows(Exception.class,()->playerDAO.delete(1));
    }
}
