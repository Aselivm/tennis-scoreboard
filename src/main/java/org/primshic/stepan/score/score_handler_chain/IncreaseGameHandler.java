package org.primshic.stepan.score.score_handler_chain;

import org.primshic.stepan.score.score.IndividualPlayerScore;
import org.primshic.stepan.score.score.State;
import org.primshic.stepan.score.score_system.Game;
import org.primshic.stepan.score.score_system.Point;
import org.primshic.stepan.score.score_system.point_types.RegularPoint;
import org.primshic.stepan.util.ScoreboardUtil;

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
            return winner.getRegularPoint() == RegularPoint.FORTY && loser.getRegularPoint() != RegularPoint.FORTY;
        } else if (state == State.ADVANTAGE) {
            return winner.getRegularPoint() == RegularPoint.AD;
        } else {
            return false;
        }
    }

    private void handleGameIncrease(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        Game increased = winnerScore.getGame().increaseCounter();
        winnerScore.setGame(increased);
        ScoreboardUtil.resetPoints(winnerScore, loserScore);
        if (increased.getCounter() == 6 && loserScore.getGame().getCounter() == 6) {
            winnerScore.getMatchScore().setState(State.TIE_BREAK);
        } else {
            winnerScore.getMatchScore().setState(State.REGULAR_GAME);
        }
    }
}
