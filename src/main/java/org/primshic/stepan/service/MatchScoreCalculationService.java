package org.primshic.stepan.service;

import lombok.AllArgsConstructor;
import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score_handler_chain.ScoreHandler;

@AllArgsConstructor
public class MatchScoreCalculationService {
    private final ScoreHandler scoreHandlerChain;

    public void calculate(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        scoreHandlerChain.handle(winnerScore, loserScore);
    }
}
