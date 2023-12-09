package org.primshic.stepan.service;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.Set;

//TODO рефакторим это, а потом все остальное
public class MatchScoreCalculationService {

    private Set winnerSet;
    private Set loserSet;
    private Game winnerGame;
    private Game loserGame;
    private Point winnerPoint;
    private Point loserPoint;

    //todo применить какой-нибудь паттерн. Например "Strategy" или "Chain of Responsibility"
    public void calculate(Score winnerScore, Score loserScore) {
        init(winnerScore, loserScore);

        if (requiresResetPoints()) {
            resetPoints(winnerScore, loserScore);
        } else if (requiresIncreasePoint()) {
            handlePointIncrease(winnerScore);
        } else if (requiresIncreaseGame()) {
            handleGameIncrease(winnerScore);
        } else {
            handleAdvantage(winnerScore, loserScore);
        }

        if (requiresIncreaseSet()) {
            handleSetIncrease(winnerScore);
        }

        if (requiresEndMatch()) {
            matchEnded();
        }
    }

    private void handlePointIncrease(Score winnerScore) {
        Point increased = winnerPoint.increaseCounter();
        winnerScore.setPoint(increased);
    }//sss

    private void handleGameIncrease(Score winnerScore) {
        Game increased = winnerGame.increaseCounter();
        winnerScore.setGame(increased);
        winnerScore.setPoint(Point.LOVE); //todo DRY. Make reset method somewhere
    }///sss

    private void handleAdvantage(Score winnerScore, Score loserScore) {
        winnerScore.setPoint(Point.AD);
        loserScore.setPoint(Point.LOVE);
    }

    private void handleSetIncrease(Score winnerScore) {
        Set increased = winnerSet.increaseCounter();
        winnerScore.setSet(increased);
        winnerScore.setGame(new Game());
        winnerScore.setPoint(Point.LOVE);
    }


    //todo реализовать и убрать отсюда

    //todo rename
    @Deprecated
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

    private boolean requiresResetPoints() {
        return loserPoint == Point.AD;
    }//ss

    private void resetPoints(Score winnerScore, Score loserScore) {
        winnerScore.setPoint(Point.FORTY);
        loserScore.setPoint(Point.FORTY);
    }//sss

    private boolean requiresIncreasePoint() {
        return winnerPoint != Point.FORTY && winnerPoint != Point.AD;
    }

    private boolean requiresIncreaseGame() {
        return winnerPoint != Point.FORTY || loserPoint != Point.FORTY;
    }//sss

    private boolean requiresIncreaseSet() {
        return winnerGame.getCounter() == 6 && (winnerGame.getCounter() - loserGame.getCounter()) >= 2
                || winnerGame.getCounter() == 7;
    }

    private boolean requiresEndMatch() {
        return winnerSet.getCounter() == 2;
    }
}
