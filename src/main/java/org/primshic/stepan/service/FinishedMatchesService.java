package org.primshic.stepan.service;

import org.primshic.stepan.dao.CompletedMatchesDAO;
import org.primshic.stepan.entity.Matches;

import java.util.LinkedList;
import java.util.List;

public class FinishedMatchesService {
    private final CompletedMatchesDAO completedMatchesDAO = new CompletedMatchesDAO();

    //todo
    public List<Matches> getAllMatches() {
        return completedMatchesDAO.index();
    }

    public List<Matches> getPage(int pageNumber) {
        List<Matches> matches = completedMatchesDAO.index();
        List<Matches> pageList = new LinkedList<>();
        //todo понадабавлять проверок
        int end = pageNumber * 5;
        int start = end - 5;
        for (int i = start; i < end; i++) {
            pageList.add(matches.get(i));
        }
        return pageList;
    }
}
