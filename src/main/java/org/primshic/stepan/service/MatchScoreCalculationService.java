package org.primshic.stepan.service;

import org.primshic.stepan.model.Score;
import org.primshic.stepan.model.score.Game;
import org.primshic.stepan.model.score.Point;
import org.primshic.stepan.model.score.Set;

public class MatchScoreCalculationService {

    public void calculate(Score winnerScore, Score loserScore) {
        Set winnerSet = winnerScore.getSet();
        Set loserSet = loserScore.getSet();
        Game winnerGame = winnerScore.getGame();
        Game loserGame = loserScore.getGame();
        Point winnerPoint = winnerScore.getPoint();
        Point loserPoint = loserScore.getPoint();

        //TODO Это пиздец какой-то)
        //TODO вместо 40 - 40 сделать РОВНО(как-нибудь)
        if (loserPoint == Point.AD) {
            winnerScore.setPoint(Point.FORTY);
            loserScore.setPoint(Point.FORTY);
        } else if (winnerPoint != Point.FORTY && winnerPoint != Point.AD) {
            Point increased = winnerPoint.increaseCounter();
            winnerScore.setPoint(increased);
        } else if (loserPoint != Point.FORTY) {
            Game increased = winnerGame.increaseCounter();//todo DRY
            winnerScore.setGame(increased);
            winnerScore.pointReset();//todo DRY
        } else if (winnerPoint == Point.AD) {
            Game increased = winnerGame.increaseCounter();//todo DRY
            winnerScore.setGame(increased);
            winnerScore.pointReset();//todo DRY
        } else {
            winnerScore.setPoint(Point.AD);
            loserScore.setPoint(Point.LOVE);
        }


        if ((winnerGame.getCounter() == 6 && (winnerGame.getCounter() - loserGame.getCounter()) >= 2)
                || winnerGame.getCounter() == 7) {
            Set increased = winnerSet.increaseCounter();
            winnerScore.setSet(increased);
            winnerScore.gameReset();
            winnerScore.pointReset();
        }

        if (winnerSet.getCounter() == 2) matchEnded();

    }


    //todo дерьмо, надо убрать отсюда
    public void matchEnded() {
        System.out.println("конец");
    }
}
