package org.primshic.stepan.controller;

import org.primshic.stepan.model.Match;
import org.primshic.stepan.model.Players;
import org.primshic.stepan.service.OngoingMatchesService;
import org.primshic.stepan.util.InputUtil;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String player1Name = InputUtil.getPlayerName(req, 1);
        String player2Name = InputUtil.getPlayerName(req, 2);

        Optional<Players> optionalPlayer1 = playersService.insertAndIgnoreDuplicate(player1Name);
        Optional<Players> optionalPlayer2 = playersService.insertAndIgnoreDuplicate(player2Name);

        Players player1;
        Players player2;

        if (optionalPlayer1.isEmpty() || optionalPlayer2.isEmpty()) {
            throw new RuntimeException("Internal error");
        }

        player1 = optionalPlayer1.get();
        player2 = optionalPlayer2.get();

        Match match = new Match(player1.getId(), player2.getId());
        UUID uuid = UUID.randomUUID();
        OngoingMatchesService.addMatch(uuid, match);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
