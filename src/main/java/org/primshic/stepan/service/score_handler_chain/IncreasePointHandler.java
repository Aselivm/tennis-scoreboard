package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_system.Point;

public class IncreasePointHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {
        Point winnerPoint = winnerScore.getPoint();
        Point loserPoint = loserScore.getPoint();
        if (requiresIncreasePoint(winnerPoint, loserPoint)) {
            handlePointIncrease(winnerScore, loserScore);
        } else {
            nextHandler.handle(winnerScore, loserScore);
        }
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private void handlePointIncrease(Score winnerScore, Score loserScore) {
        Point increased = winnerScore.getPoint().increaseCounter();
        winnerScore.setPoint(increased);
        if (increased == Point.AD) {
            loserScore.pointReset();
        }
    }

    private boolean requiresIncreasePoint(Point winnerPoint, Point loserPoint) {
        return (winnerPoint != Point.FORTY && winnerPoint != Point.AD) || (winnerPoint == Point.FORTY && loserPoint == Point.FORTY);
    }

}
