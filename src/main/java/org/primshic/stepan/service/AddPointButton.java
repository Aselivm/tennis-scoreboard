package org.primshic.stepan.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_handler_chain.ScoreHandlerChainBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddPointButton {
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService =
            new FinishedMatchesPersistenceService();

    private static final MatchScoreCalculationService matchScoreCalculation =
            new MatchScoreCalculationService(ScoreHandlerChainBuilder.buildChain());

    //todo все?
    public synchronized static void addPoint(Match match, int playerId) {
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
