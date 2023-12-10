package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.Score;
import org.primshic.stepan.service.score.State;

//Chain of responsibility
public interface ScoreHandler {
    void handle(State state, Score winnerScore, Score loserScore);

    void setNextHandler(ScoreHandler nextHandler);
}
