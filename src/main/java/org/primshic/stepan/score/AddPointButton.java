package org.primshic.stepan.score;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.score.score.IndividualPlayerScore;
import org.primshic.stepan.score.score.State;
import org.primshic.stepan.score.score_handler_chain.ScoreHandlerChainBuilder;
import org.primshic.stepan.service.FinishedMatchesPersistenceService;
import org.primshic.stepan.service.MatchScoreCalculationService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddPointButton {
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService =
            new FinishedMatchesPersistenceService();

    private static final MatchScoreCalculationService matchScoreCalculation =
            new MatchScoreCalculationService(ScoreHandlerChainBuilder.buildChain());

    public static void addPoint(Match match, int playerId) {
        IndividualPlayerScore playerScore = getPlayerScore(match, playerId);
        IndividualPlayerScore opponentScore = getOpponentScore(match, playerId);

        matchScoreCalculation.calculate(playerScore, opponentScore);

        if (match.getMatchScore().getState() == State.FINISHED) {
            handleMatchFinished(match, playerId);
        }
    }

    private static IndividualPlayerScore getPlayerScore(Match match, int playerId) {
        return (match.getPlayer1_id() == playerId) ? match.getMatchScore().getPlayer1Score()
                : match.getMatchScore().getPlayer2Score();
    }

    private static IndividualPlayerScore getOpponentScore(Match match, int playerId) {
        return (match.getPlayer1_id() != playerId) ? match.getMatchScore().getPlayer1Score()
                : match.getMatchScore().getPlayer2Score();
    }

    private static void handleMatchFinished(Match match, int playerId) {
        finishedMatchesPersistenceService.persist(match, playerId);

    }
}
