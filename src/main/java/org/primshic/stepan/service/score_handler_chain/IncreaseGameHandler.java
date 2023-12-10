package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.Score;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;

public class IncreaseGameHandler implements ScoreHandler {

    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {
        Point winnerPoint = winnerScore.getPoint();
        Point loserPoint = loserScore.getPoint();
        if (requiresIncreaseGame(winnerPoint, loserPoint)) {
            handleGameIncrease(winnerScore, loserScore);
        }
        nextHandler.handle(winnerScore, loserScore);
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresIncreaseGame(Point winnerPoint, Point loserPoint) {
        return (winnerPoint == Point.FORTY && loserPoint != Point.FORTY) || winnerPoint == Point.AD;
    }

    private void handleGameIncrease(Score winnerScore, Score loserScore) {
        Game increased = winnerScore.getGame().increaseCounter();//todo DRY
        winnerScore.setGame(increased);
        winnerScore.setPoint(new Point());
        loserScore.setPoint(new Point()); //todo dry
        if (increased.getCounter() == 6 && loserScore.getGame().getCounter() == 6) {
            winnerScore.setState(State.TIE_BREAK);
        } else {
            winnerScore.setState(State.REGULAR_GAME);
        }
    }
}
