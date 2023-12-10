package org.primshic.stepan.service;

import lombok.AllArgsConstructor;
import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_handler_chain.ScoreHandler;

@AllArgsConstructor
public class MatchScoreCalculationService {
    private final ScoreHandler scoreHandlerChain;

    public void calculate(Score winnerScore, Score loserScore) {
        scoreHandlerChain.handle(winnerScore, loserScore);
    }
}
