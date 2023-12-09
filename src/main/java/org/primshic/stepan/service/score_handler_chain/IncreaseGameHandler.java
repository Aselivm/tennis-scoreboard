package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;

public class IncreaseGameHandler implements ScoreHandler {

    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {


    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresIncreaseGame(Point winnerPoint, Point loserPoint) {
        return winnerPoint != Point.FORTY || loserPoint != Point.FORTY;
    }

    private void handleGameIncrease(Score winnerScore) {
        Game increased = winnerScore.getGame().increaseCounter();//todo DRY
        winnerScore.setGame(increased);
        winnerScore.setPoint(Point.LOVE); //todo DRY. Make reset method somewhere
    }
}
