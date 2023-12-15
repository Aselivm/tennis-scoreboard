package org.primshic.stepan.controller;

import org.primshic.stepan.entity.Matches;
import org.primshic.stepan.service.FinishedMatchesPersistenceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Matches", urlPatterns = "/matches")
public class MatchesServlet extends BaseServlet {
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterByPlayerName = req.getParameter("filter_by_player_name");
        String numberStr = req.getParameter("page");//todo перекинуть в утил
        int pageNumber = Integer.parseInt(numberStr);//todo try-catch number Format все дела
        List<Matches> page;
        if (filterByPlayerName != null) {
            page = finishedMatchesPersistenceService.getPageByName(pageNumber, filterByPlayerName);
        } else {
            page = finishedMatchesPersistenceService.getPage(pageNumber);
        }
        req.setAttribute("playerName", filterByPlayerName);
        req.setAttribute("pageList", page);
        req.setAttribute("pageNumber", pageNumber);
        req.getRequestDispatcher(pathToViews + "matches.jsp").forward(req, resp);
    }
}
