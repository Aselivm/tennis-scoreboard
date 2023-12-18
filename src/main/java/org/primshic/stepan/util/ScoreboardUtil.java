package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.model.Players;
import org.primshic.stepan.score.score.IndividualPlayerScore;
import org.primshic.stepan.score.score.MatchScore;
import org.primshic.stepan.score.score_system.Game;
import org.primshic.stepan.score.score_system.Point;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreboardUtil {
    public static Players getWinnerById(int winnerId, Players player1, Players player2) {
        Players winner;
        if (player1.getId() == winnerId) {
            winner = player1;
        } else if (player2.getId() == winnerId) {
            winner = player2;
        } else {
            throw new RuntimeException("Internal error");
        }
        return winner;
    }

    public static Players getWinnerByScore(MatchScore matchScore, Players player1, Players player2) {
        Players winner;
        if (matchScore.getPlayer1Score().getSet().getCounter() == 2) {
            winner = player1;
        } else if (matchScore.getPlayer2Score().getSet().getCounter() == 2) {
            winner = player2;
        } else {
            throw new RuntimeException("Internal error");
        }
        return winner;
    }

    public static void resetPoints(IndividualPlayerScore winner, IndividualPlayerScore loser) {
        winner.setPoint(new Point());
        loser.setPoint(new Point());
    }

    public static void resetPoints(IndividualPlayerScore playerScore) {
        playerScore.setPoint(new Point());
    }

    public static void resetGames(IndividualPlayerScore winner, IndividualPlayerScore loser) {
        winner.setGame(new Game());
        loser.setGame(new Game());
    }

    public static void resetGames(IndividualPlayerScore playerScore) {
        playerScore.setGame(new Game());
    }
}
