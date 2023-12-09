package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;

//Chain of responsibility
public interface ScoreHandler {
    void handle(Score winnerScore, Score loserScore);

    void setNextHandler(ScoreHandler nextHandler);
}
