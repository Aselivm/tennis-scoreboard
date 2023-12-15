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
        String numberStr = req.getParameter("page");//todo перекинуть в утил
        int pageNumber = Integer.parseInt(numberStr);
        List<Matches> page = finishedMatchesPersistenceService.getPage(pageNumber);
        req.setAttribute("pageList", page);
        req.setAttribute("pageNumber", pageNumber);
        req.getRequestDispatcher(pathToViews + "matches.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numberStr = req.getParameter("page");//todo перекинуть в утил
        int pageNumber = Integer.parseInt(numberStr);
        String value = req.getParameter("pagination");
        int paginationValue = Integer.parseInt(value);
        if (paginationValue != 0) {
            if (paginationValue == 1) {
                int next = pageNumber + 1;
                resp.sendRedirect("/matches?page=" + next);
            } else if (pageNumber != 1) {
                int prev = pageNumber - 1;
                resp.sendRedirect("/matches?page=" + prev);
            }
        }
    }
}
