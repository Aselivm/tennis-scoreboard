package org.primshic.stepan.controller;

import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.OngoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@WebServlet(name = "New match", urlPatterns = "/new-match")
public class NewMatchServlet extends BaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(pathToViews + "new_match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = req.getParameter("player_name_1");
        String player2Name = req.getParameter("player_name_2");

        Optional<Players> player1 = playersService.getEntity(player1Name);
        Optional<Players> player2 = playersService.getEntity(player2Name);

        Match match = new Match(player1.get().getId(), player2.get().getId());

        UUID uuid = UUID.randomUUID();

        OngoingMatchesService.addMatch(uuid, match);
        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
