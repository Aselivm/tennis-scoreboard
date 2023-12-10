package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.IndividualPlayerScore;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.Set;

public class MatchEndHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(IndividualPlayerScore winnerScore, IndividualPlayerScore loserScore) {
        Set winnerSet = winnerScore.getSet();
        if (requiresMatchEnd(winnerSet)) {
            handleMatchEnd(winnerScore);
        }
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresMatchEnd(Set winnerSet) {
        return winnerSet.getCounter() == 2;
    }

    private void handleMatchEnd(IndividualPlayerScore winner) {
        winner.getMatchScore().setState(State.FINISHED);
    }
}
