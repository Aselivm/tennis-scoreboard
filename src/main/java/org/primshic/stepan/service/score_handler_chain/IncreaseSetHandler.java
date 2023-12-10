package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.Set;

public class IncreaseSetHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {
        Game winnerGame = winnerScore.getGame();
        Game loserGame = loserScore.getGame();
        if (requiresIncreaseSet(winnerGame, loserGame)) {
            handleSetIncrease(winnerScore, loserScore);
        }
        nextHandler.handle(winnerScore, loserScore);
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresIncreaseSet(Game winnerGame, Game loserGame) {
        return winnerGame.getCounter() == 6 && (winnerGame.getCounter() - loserGame.getCounter()) >= 2
                || winnerGame.getCounter() == 7;
    }

    private void handleSetIncrease(Score winnerScore, Score loserScore) {
        Set increased = winnerScore.getSet().increaseCounter();
        winnerScore.setSet(increased);
        winnerScore.setGame(new Game());
        winnerScore.setPoint(Point.LOVE);
        loserScore.gameReset();
    }
}
