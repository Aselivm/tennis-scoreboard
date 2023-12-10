package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.IndividualPlayerScore;

//Chain of responsibility
public interface ScoreHandler {
    void handle(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore);

    void setNextHandler(ScoreHandler nextHandler);
}
