package org.primshic.stepan.score.score_handler_chain;

import org.primshic.stepan.score.score.IndividualPlayerScore;
import org.primshic.stepan.score.score.State;
import org.primshic.stepan.score.score_system.Point;
import org.primshic.stepan.score.score_system.point_types.RegularPoint;

public class EqualizeHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        Point loserPoint = loserScore.getPoint();
        if (requiresEqualize(loserPoint)) {
            handleEqualize(winnerScore, loserScore);
        } else {
            nextHandler.handle(winnerScore, loserScore);
        }
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresEqualize(Point loser) {
        RegularPoint loserPoint = loser.getRegularPoint();
        return loserPoint == RegularPoint.AD;
    }

    private void handleEqualize(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        winnerScore.getMatchScore().setState(State.REGULAR_GAME);
        winnerScore.setPoint(new Point(RegularPoint.FORTY));
        loserScore.setPoint(new Point(RegularPoint.FORTY));
    }
}
