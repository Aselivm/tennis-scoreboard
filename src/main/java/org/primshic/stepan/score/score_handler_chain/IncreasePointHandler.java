package org.primshic.stepan.score.score_handler_chain;

import org.primshic.stepan.score.score.IndividualPlayerScore;
import org.primshic.stepan.score.score.State;
import org.primshic.stepan.score.score_system.Point;
import org.primshic.stepan.score.score_system.point_types.RegularPoint;
import org.primshic.stepan.score.score_system.point_types.TieBreakPoint;

public class IncreasePointHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        State state = winnerScore.getMatchScore().getState();
        Point winnerPoint = winnerScore.getPoint();
        Point loserPoint = loserScore.getPoint();
        if (requiresIncreasePoint(state, winnerPoint, loserPoint)) {
            handlePointIncrease(winnerScore, loserScore);
        } else {
            nextHandler.handle(winnerScore, loserScore);
        }
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private void handlePointIncrease(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        State state = winnerScore.getMatchScore().getState();
        Point winnerPoint = winnerScore.getPoint();
        Point increased = null;
        if (state == State.REGULAR_GAME) {
            RegularPoint regularPointIncrease = winnerScore.getPoint().getRegularPoint().increaseCounter();
            increased = new Point(regularPointIncrease);
            if (increased.getRegularPoint() == RegularPoint.AD) {
                winnerScore.getMatchScore().setState(State.ADVANTAGE);
            }
        } else if (state == State.TIE_BREAK && winnerPoint.getCounter() != 6) {
            TieBreakPoint tieBreakPointIncrease = winnerScore.getPoint().getTieBreakPoint().increaseCounter();
            increased = new Point(tieBreakPointIncrease);
        }

        winnerScore.setPoint(increased);
    }

    private boolean requiresIncreasePoint(State state, Point winner, Point loser) {
        if (state == State.REGULAR_GAME) {
            RegularPoint winnerPoint = winner.getRegularPoint();
            RegularPoint loserPoint = loser.getRegularPoint();
            return winnerPoint != RegularPoint.FORTY || loserPoint == RegularPoint.FORTY;
        } else if (state == State.TIE_BREAK) {
            return winner.getTieBreakPoint().getCounter() != 6;
        } else if (state == State.ADVANTAGE) {
            RegularPoint winnerPoint = winner.getRegularPoint();
            return winnerPoint != RegularPoint.AD;
        }
        throw new RuntimeException("Internal error");
    }

}
