package org.primshic.stepan.controller;

import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.OngoingMatchesService;
import org.primshic.stepan.service.score.MatchScore;
import org.primshic.stepan.service.score.State;
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

        Optional<Players> player1 = playersService.getById(match.getPlayer1_id()); //todo 2 запроса в бд, сделать 1
        Optional<Players> player2 = playersService.getById(match.getPlayer2_id());
        req.setAttribute("player1", player1.get());
        req.setAttribute("player2", player2.get());

        req.setAttribute("matchScore", matchScore);
        if (matchScore.getState() == State.FINISHED) {
            Players winner = null;
            if (matchScore.getPlayer1Score().getSet().getCounter() == 2) {
                winner = player1.get();
            } else if (matchScore.getPlayer2Score().getSet().getCounter() == 2) {
                winner = player2.get();
            } else {
                //todo throw exception
            }
            req.setAttribute("winner", winner);
            req.getRequestDispatcher(pathToViews + "match_finished.jsp").forward(req, resp);
            OngoingMatchesService.removeMatch(uuid);
        } else {
            req.setAttribute("uuid", uuid);
            req.getRequestDispatcher(pathToViews + "match_score.jsp").forward(req, resp);
        }
    }

    //todo реализовать маппер для вывода изображение. Например у меня AD отображается как единичка, а это неправильно
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        String player = req.getParameter("player");
        int playerId = 0;
        if (player != null) {
            playerId = Integer.parseInt(player);
        }//todo перенести куда-нибудь в Util
        ScoreboardUtil.addPoint(match, playerId);
        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
