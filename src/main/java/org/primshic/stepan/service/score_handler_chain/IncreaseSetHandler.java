package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;

public class IncreaseSetHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {

    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


}
