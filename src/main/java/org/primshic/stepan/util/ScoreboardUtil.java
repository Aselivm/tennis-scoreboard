package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.entity.Players;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.FinishedMatchesPersistenceService;
import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score.MatchScore;
import org.primshic.stepan.service.score.State;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreboardUtil {
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService =
            new FinishedMatchesPersistenceService();

    public static Players getWinner(MatchScore matchScore, Players player1, Players player2) {
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

    public static void addPoint(Match match, int playerId) {
        IndividualPlayerScore playerScore = getPlayerScore(match, playerId);
        IndividualPlayerScore opponentScore = getOpponentScore(match, playerId);

        match.getMatchScore().addPoint(playerScore, opponentScore);

        if (match.getMatchScore().getState() == State.FINISHED) {
            handleMatchFinished(match, playerId);
        }
    }

    //todo проверить
    private static IndividualPlayerScore getPlayerScore(Match match, int playerId) {
        return (match.getPlayer1_id() == playerId) ? match.getMatchScore().getPlayer1Score()
                : match.getMatchScore().getPlayer2Score();
    }

    //todo проверить
    private static IndividualPlayerScore getOpponentScore(Match match, int playerId) {
        return (match.getPlayer1_id() != playerId) ? match.getMatchScore().getPlayer1Score()
                : match.getMatchScore().getPlayer2Score();
    }

    //todo ну ебаный в рот валера, это не отсюда
    private static void handleMatchFinished(Match match, int playerId) {
        finishedMatchesPersistenceService.persist(match, playerId);

    }
}
