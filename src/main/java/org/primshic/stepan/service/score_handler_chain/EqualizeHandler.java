package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_system.Point;

public class EqualizeHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {
        if (requiresEqualize(loserScore.getPoint())) {
            equalize(winnerScore, loserScore);
            return;
        }
        nextHandler.handle(winnerScore, loserScore);
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresEqualize(Point loserPoint) {
        return loserPoint == Point.AD;
    }

    private void equalize(Score winnerScore, Score loserScore) {
        winnerScore.setPoint(Point.FORTY);
        loserScore.setPoint(Point.FORTY);
    }
}
