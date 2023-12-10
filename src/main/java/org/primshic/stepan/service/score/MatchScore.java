package org.primshic.stepan.service.score;

import lombok.Getter;
import org.primshic.stepan.service.MatchScoreCalculationService;
import org.primshic.stepan.service.score_handler_chain.ScoreHandlerChainBuilder;

@Getter
public class MatchScore {
    private final MatchScoreCalculationService matchScoreCalculation;

    public MatchScore() {
        matchScoreCalculation = new MatchScoreCalculationService(ScoreHandlerChainBuilder.buildChain());
        this.state = State.REGULAR_GAME;
        this.player1Score = new Score();
        this.player2Score = new Score();
    }

    private State state;

    private final Score player1Score;

    private final Score player2Score;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    //todo эту залупу перенести
    public void addPoint(Score winnerScore, Score loserScore) {
        matchScoreCalculation.calculate(state, winnerScore, loserScore);
    }

}
