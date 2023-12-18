package org.primshic.stepan.service;

import org.primshic.stepan.dao.CompletedMatches;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.model.Matches;
import org.primshic.stepan.model.Players;
import org.primshic.stepan.util.ScoreboardUtil;

import java.util.List;
import java.util.Optional;


public class FinishedMatchesPersistenceService {
    public FinishedMatchesPersistenceService() {
        init();
    }

    private static boolean initCompleted = false;
    private final CompletedMatches completedMatchesDAO = new CompletedMatches();
    private final PlayersService playersService = new PlayersService();

    public void persist(Match match, int winnerId) {
        Optional<Players> optionalPlayer1 = playersService.getById(match.getPlayer1_id());
        Optional<Players> optionalPlayer2 = playersService.getById(match.getPlayer2_id());
        Matches matches = new Matches();

        Players player1;
        Players player2;

        if (optionalPlayer1.isPresent() && optionalPlayer2.isPresent()) {
            player1 = optionalPlayer1.get();
            player2 = optionalPlayer2.get();
        } else {
            throw new RuntimeException("Internal error");
        }

        Players winner = ScoreboardUtil.getWinnerById(winnerId, player1, player2);

        matches.setPlayers1(player1);
        matches.setPlayers2(player2);
        matches.setWinner(winner);
        completedMatchesDAO.save(matches);
    }

    public List<Matches> getPageByName(int pageNumber, String playerName) {
        return completedMatchesDAO.pageIndexByName(pageNumber, playerName);
    }

    public List<Matches> getPage(int pageNumber) {
        return completedMatchesDAO.pageIndex(pageNumber);
    }

    private void init() {
        if (!initCompleted) {
            for (int i = 0; i < 20; i++) {
                Players player1 = playersService.insertAndIgnoreDuplicate("STEVE " + i).get();
                Players player2 = playersService.insertAndIgnoreDuplicate("JOHN " + i).get();
                Match match = new Match(player1.getId(), player2.getId());
                if (i % 2 == 0) {
                    persist(match, player1.getId());
                } else {
                    persist(match, player2.getId());
                }

            }
            initCompleted = true;
        }
    }
}
