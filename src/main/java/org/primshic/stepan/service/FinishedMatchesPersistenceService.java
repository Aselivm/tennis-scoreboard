package org.primshic.stepan.service;

import org.primshic.stepan.dao.CompletedMatchesDAO;
import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;

import java.util.LinkedList;
import java.util.List;
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

    //todo метод не отсюда
    public List<Matches> getPageByName(int pageNumber, String playerName) {
        List<Matches> matches = completedMatchesDAO.indexByName(playerName);
        List<Matches> pageList = new LinkedList<>();
        int pageSize = 5;
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(pageNumber * pageSize, matches.size());
        for (int i = start; i < end; i++) {
            if (i > matches.size()) {
                pageList.add(matches.get(i));
            }
            pageList.add(matches.get(i));
        }
        return pageList;
    }

    //todo метод не отсюда
    public List<Matches> getPage(int pageNumber) {
        List<Matches> matches = completedMatchesDAO.index();
        List<Matches> pageList = new LinkedList<>();
        int pageSize = 5;
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(pageNumber * pageSize, matches.size());
        for (int i = start; i < end; i++) {
            if (i > matches.size()) {
                pageList.add(matches.get(i));
            }
            pageList.add(matches.get(i));
        }
        return pageList;
    }

    private void init() {
        for (int i = 0; i < 20; i++) {
            Players player1 = playersService.save("Steve " + i).get();
            Players player2 = playersService.save("John " + i).get();
            Match match = new Match(player1.getId(), player2.getId());
            System.out.println("here");
            if (i % 2 == 0) {
                persist(match, player1.getId());
            } else {
                persist(match, player2.getId());
            }

        }


    }
}
