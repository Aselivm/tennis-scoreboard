package org.primshic.stepan;

import org.junit.Assert;
import org.junit.Test;
import org.primshic.stepan.dao.CompletedMatchesDAO;
import org.primshic.stepan.dao.PlayersDAO;
import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.entity.Players;

public class DAOTest {


    @Test
    public void dao() {
        Players players1 = new Players("SteveTEST");
        Players players2 = new Players("TomTEST");
        Matches matches = new Matches();
        matches.setPlayers1(players1);
        matches.setPlayers2(players2);
        CompletedMatchesDAO completedMatchesDAO = new CompletedMatchesDAO();
        PlayersDAO playersDAO = new PlayersDAO();

        playersDAO.save(players1);
        playersDAO.save(players2);
        completedMatchesDAO.save(matches);

        completedMatchesDAO.index();

        System.out.println(playersDAO.indexByName("SteveTEST").get(0).getName());
        System.out.println(playersDAO.indexByName("TomTEST").get(0).getName());

        completedMatchesDAO.delete(1);
        Assert.assertThrows(Exception.class, () -> playersDAO.delete(1));
    }
}
