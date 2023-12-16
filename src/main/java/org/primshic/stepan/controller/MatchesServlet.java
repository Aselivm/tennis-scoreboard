package org.primshic.stepan.controller;

import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.util.InputUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Matches", urlPatterns = "/matches")
public class MatchesServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterByPlayerName = InputUtil.filterByPlayerName(req);
        int pageNumber = InputUtil.getPageNumber(req);
        List<Matches> page;
        List<Matches> nextPage;
        if (filterByPlayerName.length() > 0) {
            page = finishedMatchesPersistenceService.getPageByName(pageNumber, filterByPlayerName);
            nextPage = finishedMatchesPersistenceService.getPageByName(pageNumber + 1, filterByPlayerName);
        } else {
            page = finishedMatchesPersistenceService.getPage(pageNumber);
            nextPage = finishedMatchesPersistenceService.getPage(pageNumber + 1);
        }
        boolean next = nextPage.size() != 0;
        req.setAttribute("next", next);
        req.setAttribute("playerName", filterByPlayerName);
        req.setAttribute("pageList", page);
        req.setAttribute("pageNumber", pageNumber);
        req.getRequestDispatcher(pathToViews + "matches.jsp").forward(req, resp);
    }
}
