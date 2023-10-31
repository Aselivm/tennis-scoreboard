package org.primshic.stepan;

import org.junit.Assert;
import org.junit.Test;
import org.primshic.stepan.dao.MatchDAO;
import org.primshic.stepan.dao.PlayerDAO;
import org.primshic.stepan.entity.Match;
import org.primshic.stepan.entity.Player;

public class DAOTest {


    @Test
    public void dao(){
        Player player1 = new Player("SteveTEST");
        Player player2 = new Player("TomTEST");
        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        MatchDAO matchDAO = new MatchDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        playerDAO.save(player1);
        playerDAO.save(player2);
        matchDAO.save(match);

        matchDAO.index();

        System.out.println(playerDAO.showByName("SteveTEST").get(0).getName());
        System.out.println(playerDAO.showByName("TomTEST").get(0).getName());

        matchDAO.delete(1);
        Assert.assertThrows(Exception.class,()->playerDAO.delete(1));
    }
}
