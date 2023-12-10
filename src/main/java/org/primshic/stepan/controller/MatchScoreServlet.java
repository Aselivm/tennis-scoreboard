package org.primshic.stepan.controller;

import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.OngoingMatchesService;
import org.primshic.stepan.service.PlayersService;
import org.primshic.stepan.service.score.MatchScore;
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
    private PlayersService playersService = new PlayersService();//todo в общий

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        Optional<Players> player1 = playersService.getById(match.getPlayer1_id()); //todo 2 запроса в бд, сделать 1
        Optional<Players> player2 = playersService.getById(match.getPlayer2_id());
        MatchScore matchScore = match.getMatchScore();
        req.setAttribute("player1", player1.get());
        req.setAttribute("player2", player2.get());
        req.setAttribute("matchScore", matchScore);
        req.setAttribute("uuid", uuid);
        req.getRequestDispatcher(pathToViews + "match_score.jsp").forward(req, resp);
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
