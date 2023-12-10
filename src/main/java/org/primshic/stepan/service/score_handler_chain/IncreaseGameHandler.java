package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.point_types.RegularPoint;

public class IncreaseGameHandler implements ScoreHandler {

    private ScoreHandler nextHandler;

    @Override
    public void handle(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        State state = winnerScore.getMatchScore().getState();
        Point winnerPoint = winnerScore.getPoint();
        Point loserPoint = loserScore.getPoint();
        if (requiresIncreaseGame(state, winnerPoint, loserPoint)) {
            handleGameIncrease(winnerScore, loserScore);
        }
        nextHandler.handle(winnerScore, loserScore);
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresIncreaseGame(State state, Point winner, Point loser) {
        if (state == State.REGULAR_GAME) {
            RegularPoint winnerPoint = winner.getRegularPoint();
            RegularPoint loserPoint = loser.getRegularPoint();
            return winnerPoint == RegularPoint.FORTY && loserPoint != RegularPoint.FORTY;
        } else if (state == State.ADVANTAGE) {
            RegularPoint winnerPoint = winner.getRegularPoint();//todo dry
            return winnerPoint == RegularPoint.AD;
        }

        //todo change
        try {
            throw new Exception();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleGameIncrease(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        Game increased = winnerScore.getGame().increaseCounter();//todo DRY
        winnerScore.setGame(increased);
        winnerScore.setPoint(new Point());
        loserScore.setPoint(new Point()); //todo dry
        if (increased.getCounter() == 6 && loserScore.getGame().getCounter() == 6) {
            winnerScore.getMatchScore().setState(State.TIE_BREAK);
        } else {
            winnerScore.getMatchScore().setState(State.REGULAR_GAME);
        }
    }
}
