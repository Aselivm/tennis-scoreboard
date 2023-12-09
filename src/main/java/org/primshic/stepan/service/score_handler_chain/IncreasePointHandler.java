package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_system.Point;

public class IncreasePointHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {

    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private void handlePointIncrease(Score winnerScore) {
        Point increased = winnerScore.getPoint().increaseCounter();
        winnerScore.setPoint(increased);
    }

    private boolean requiresIncreasePoint(Point winnerPoint) {
        return winnerPoint != Point.FORTY && winnerPoint != Point.AD;
    }
}
