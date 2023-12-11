package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.FinishedMatchesPersistenceService;
import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score.State;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreboardUtil {
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService =
            new FinishedMatchesPersistenceService();

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

    private static void handleMatchFinished(Match match, int playerId) {
        finishedMatchesPersistenceService.persist(match, playerId);

    }

    //todo перенести
    private static IndividualPlayerScore throwInvalidPlayerIdException() {
        throw new IllegalArgumentException("PlayerId doesn't match player1_id or player2_id");
    }
}
