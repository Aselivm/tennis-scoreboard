package org.primshic.stepan.score.score_handler_chain;

import org.primshic.stepan.score.score.IndividualPlayerScore;
import org.primshic.stepan.score.score.State;
import org.primshic.stepan.score.score_system.Set;
import org.primshic.stepan.score.score_system.point_types.TieBreakPoint;
import org.primshic.stepan.util.ScoreboardUtil;

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
        TieBreakPoint winnerPoint = winner.getPoint().getTieBreakPoint();
        if (state == State.TIE_BREAK) {
            return winnerPoint.getCounter() == 6;
        } else {
            int winnerGames = winner.getGame().getCounter();
            int loserGames = loser.getGame().getCounter();
            return winnerGames == 6 && (winnerGames - loserGames) >= 2;
        }
    }

    private void handleSetIncrease(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        Set increased = winnerScore.getSet().increaseCounter();
        winnerScore.setSet(increased);
        winnerScore.getMatchScore().setState(State.REGULAR_GAME);
        ScoreboardUtil.resetPoints(winnerScore, loserScore);
        ScoreboardUtil.resetGames(winnerScore, loserScore);
    }
}
