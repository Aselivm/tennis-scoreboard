package org.primshic.stepan.service;

import org.primshic.stepan.dao.CompletedMatchesDAO;
import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.util.ScoreboardUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class FinishedMatchesPersistenceService {
    public FinishedMatchesPersistenceService() {
        init();
    }

    private static boolean initCompleted = false;
    private final CompletedMatchesDAO completedMatchesDAO = new CompletedMatchesDAO();
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
        List<Matches> matches = completedMatchesDAO.indexByName(playerName);
        return getMatches(pageNumber, matches);
    }

    public List<Matches> getPage(int pageNumber) {
        List<Matches> matches = completedMatchesDAO.index();
        return getMatches(pageNumber, matches);
    }

    private List<Matches> getMatches(int pageNumber, List<Matches> matches) {
        List<Matches> pageList = new LinkedList<>();
        int pageSize = 5;
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(pageNumber * pageSize, matches.size());
        for (int i = start; i < end; i++) {
            pageList.add(matches.get(i));
        }
        return pageList;
    }

    //todo сделать нормальный список
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
