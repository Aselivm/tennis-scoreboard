package org.primshic.stepan.service;

import org.primshic.stepan.dao.CompletedMatchesDAO;
import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;

import java.util.Optional;

public class FinishedMatchesPersistenceService {
    public FinishedMatchesPersistenceService() {
        init();
    }

    private final CompletedMatchesDAO completedMatchesDAO = new CompletedMatchesDAO();
    private final PlayersService playersService = new PlayersService();

    public void persist(Match match, int winnerId) {
        Optional<Players> players1 = playersService.getById(match.getPlayer1_id());//todo 3 вызова в бд
        Optional<Players> players2 = playersService.getById(match.getPlayer2_id());//todo 3 вызова в бд
        Optional<Players> winner = playersService.getById(winnerId);
        Matches matches = new Matches();
        matches.setPlayers1(players1.get());//todo сделать поумнее
        matches.setPlayers2(players2.get());
        matches.setWinner(winner.get());
        completedMatchesDAO.save(matches);
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            Players player1 = playersService.save("Steve " + i).get();
            Players player2 = playersService.save("John " + i).get();
            Match match = new Match(player1.getId(), player2.getId());
            if (i % 2 == 0) {
                persist(match, player1.getId());
            } else {
                persist(match, player2.getId());
            }

        }


    }
}
