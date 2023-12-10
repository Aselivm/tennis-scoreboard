package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.Score;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.Point;

public class EqualizeHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(State state, Score winnerScore, Score loserScore) {
        Point loserPoint = loserScore.getPoint();
        if (requiresEqualize(loserPoint)) {
            handleEqualize(winnerScore, loserScore);
        } else {
            nextHandler.handle(state, winnerScore, loserScore);
        }
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresEqualize(Point loserPoint) {
        return loserPoint == Point.AD;
    }

    private void handleEqualize(Score winnerScore, Score loserScore) {
        winnerScore.setPoint(Point.FORTY);
        loserScore.setPoint(Point.FORTY);
    }
}
