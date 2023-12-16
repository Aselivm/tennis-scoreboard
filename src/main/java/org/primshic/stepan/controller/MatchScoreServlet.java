package org.primshic.stepan.controller;

import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.AddPointButton;
import org.primshic.stepan.service.OngoingMatchesService;
import org.primshic.stepan.service.score.MatchScore;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.util.InputUtil;
import org.primshic.stepan.util.ScoreboardUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "Match score", urlPatterns = "/match-score")
public class MatchScoreServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);
        MatchScore matchScore = match.getMatchScore();

        Optional<Players> optionalPlayer1 = playersService.getById(match.getPlayer1_id());
        Optional<Players> optionalPlayer2 = playersService.getById(match.getPlayer2_id());

        Players player1;
        Players player2;

        if (optionalPlayer1.isPresent() && optionalPlayer2.isPresent()) {
            player1 = optionalPlayer1.get();
            player2 = optionalPlayer2.get();
        } else {
            throw new RuntimeException("Internal error");
        }

        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.setAttribute("matchScore", matchScore);

        if (matchScore.getState() == State.FINISHED) {
            Players winner = ScoreboardUtil.getWinnerByScore(matchScore, player1, player2);
            req.setAttribute("winner", winner);
            req.getRequestDispatcher(pathToViews + "match_finished.jsp").forward(req, resp);
            OngoingMatchesService.removeMatch(uuid);
        } else {
            req.setAttribute("uuid", uuid);
            req.getRequestDispatcher(pathToViews + "match_score.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        int playerId = InputUtil.getPlayerId(req);
        AddPointButton.addPoint(match, playerId);
        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
