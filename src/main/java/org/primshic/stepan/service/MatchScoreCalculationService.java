package org.primshic.stepan.service;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.model.score.Game;
import org.primshic.stepan.model.score.Point;
import org.primshic.stepan.model.score.Set;

//TODO рефакторим это, а потом все остальное
public class MatchScoreCalculationService {

    private Set winnerSet;
    private Set loserSet;
    private Game winnerGame;
    private Game loserGame;
    private Point winnerPoint;
    private Point loserPoint;

    public void calculate(Score winnerScore, Score loserScore) {
        init(winnerScore, loserScore);

        if (shouldResetPoints()) {
            resetPoints(winnerScore, loserScore);
        } else if (shouldIncreasePoint(winnerPoint)) {
            handlePointIncrease(winnerScore);
        } else if (shouldIncreaseGame(loserPoint) || shouldIncreaseGame(winnerPoint)) {
            handleGameIncrease(winnerScore);
        } else {
            handleDeuce(winnerScore, loserScore);
        }

        if (shouldIncreaseSet()) {
            handleSetIncrease(winnerScore);
        }

        if (shouldEndMatch()) {
            matchEnded();
        }
    }

    private void handlePointIncrease(Score winnerScore) {
        Point increased = winnerPoint.increaseCounter();
        winnerScore.setPoint(increased);
    }

    private void handleGameIncrease(Score winnerScore) {
        Game increased = winnerGame.increaseCounter();
        winnerScore.setGame(increased);
        winnerScore.pointReset();
    }

    private void handleDeuce(Score winnerScore, Score loserScore) {
        winnerScore.setPoint(Point.AD);
        loserScore.setPoint(Point.LOVE);
    }

    private void handleSetIncrease(Score winnerScore) {
        Set increased = winnerSet.increaseCounter();
        winnerScore.setSet(increased);
        winnerScore.gameReset();
        winnerScore.pointReset();
    }


    //todo реализовать и убрать отсюда

    public void matchEnded() {
        System.out.println("конец");
    }

    private void init(Score winnerScore, Score loserScore) {
        winnerSet = winnerScore.getSet();
        loserSet = loserScore.getSet();
        winnerGame = winnerScore.getGame();
        loserGame = loserScore.getGame();
        winnerPoint = winnerScore.getPoint();
        loserPoint = loserScore.getPoint();
    }

    private boolean shouldResetPoints() {
        return loserPoint == Point.AD;
    }

    private void resetPoints(Score winnerScore, Score loserScore) {
        winnerScore.setPoint(Point.FORTY);
        loserScore.setPoint(Point.FORTY);
    }

    private boolean shouldIncreasePoint(Point point) {
        return point != Point.FORTY && point != Point.AD;
    }

    private boolean shouldIncreaseGame(Point point) {
        return point != Point.FORTY;
    }

    private boolean shouldIncreaseSet() {
        return winnerGame.getCounter() == 6 && (winnerGame.getCounter() - loserGame.getCounter()) >= 2
                || winnerGame.getCounter() == 7;
    }

    private boolean shouldEndMatch() {
        return winnerSet.getCounter() == 2;
    }
}
