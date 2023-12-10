package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.Set;
import org.primshic.stepan.service.score_system.point_types.TieBreakPoint;

public class IncreaseSetHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {

        if (requiresIncreaseSet(winnerScore, loserScore)) {
            handleSetIncrease(winnerScore, loserScore);
        }
        nextHandler.handle(winnerScore, loserScore);
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresIncreaseSet(IndividualPlayerScore winner, IndividualPlayerScore loser) {
        State state = winner.getMatchScore().getState();
        if (state == State.TIE_BREAK) {
            TieBreakPoint winnerPoint = winner.getPoint().getTieBreakPoint().increaseCounter();
            return winnerPoint.getCounter() == 7;
        } else {
            return winner.getGame().getCounter() == 6 && (winner.getGame().getCounter() - loser.getGame().getCounter()) >= 2; //todo shorten to "winner" and "loser"
        }
    }

    private void handleSetIncrease(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        Set increased = winnerScore.getSet().increaseCounter();
        winnerScore.setSet(increased);
        winnerScore.pointReset();
        winnerScore.gameReset();
        loserScore.pointReset();
        loserScore.gameReset();
    }
}
