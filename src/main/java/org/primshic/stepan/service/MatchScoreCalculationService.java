package org.primshic.stepan.service;

import lombok.AllArgsConstructor;
import org.primshic.stepan.service.score.Score;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_handler_chain.ScoreHandler;

@AllArgsConstructor
public class MatchScoreCalculationService {
    private final ScoreHandler scoreHandlerChain;

    public void calculate(State state, Score winnerScore, Score loserScore) {
        scoreHandlerChain.handle(state, winnerScore, loserScore);
    }
}
