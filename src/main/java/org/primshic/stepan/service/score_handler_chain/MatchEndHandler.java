package org.primshic.stepan.service.score_handler_chain;

import org.primshic.stepan.service.score.Score;
import org.primshic.stepan.service.score_system.Set;

public class MatchEndHandler implements ScoreHandler {
    private ScoreHandler nextHandler;

    @Override
    public void handle(Score winnerScore, Score loserScore) {
        Set winnerSet = winnerScore.getSet();
        if (requiresMatchEnd(winnerSet)) {
            handleMatchEnd();
        }
    }

    @Override
    public void setNextHandler(ScoreHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    private boolean requiresMatchEnd(Set winnerSet) {
        return winnerSet.getCounter() == 2;
    }

    private void handleMatchEnd() {
        //todo handle it
        System.out.println("TO DO");
    }
}
