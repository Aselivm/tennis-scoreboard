package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.Score;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.point_types.RegularPoint;
import org.primshic.stepan.service.score.State;

public class IncreasePointHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {
        Point winnerPoint = winnerScore.getPoint();
        Point loserPoint = loserScore.getPoint();
        if (requiresIncreasePoint(,winnerPoint, loserPoint)) {
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
        if (increased.getState() == State.ADVANTAGE) {
            loserScore.pointReset();
        }
    }

    private boolean requiresIncreasePoint(State state, Point winner, Point loser) {
        if (state == State.REGULAR_GAME) {
            RegularPoint winnerPoint = winner.getRegularPoint();
            RegularPoint loserPoint = loser.getRegularPoint();
            //todo в ретерне есть AD, а это должно быть невозможно
            return (winnerPoint != RegularPoint.FORTY && winnerPoint != RegularPoint.AD) || (winnerPoint == RegularPoint.FORTY && loserPoint == RegularPoint.FORTY);
        } else if (state == State.TIE_BREAK) {
            return winner.getTieBreakPoint().getCounter() != 7;
        } else if (state == State.ADVANTAGE) {

        }
    }

}
