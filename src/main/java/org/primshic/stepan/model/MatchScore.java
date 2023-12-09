package org.primshic.stepan.model;

import lombok.Getter;
import org.primshic.stepan.service.MatchScoreCalculationService;

@Getter
public class MatchScore {
    private final MatchScoreCalculationService matchScoreCalculation;

    public MatchScore() {
        matchScoreCalculation = new MatchScoreCalculationService();
        this.player1Score = new Score();
        this.player2Score = new Score();
    }

    private final Score player1Score;
    private final Score player2Score;

    //todo эту залупу перенести
    public void addPoint(Score winner, Score loser) {
        matchScoreCalculation.calculate(winner, loser);
    }

}
