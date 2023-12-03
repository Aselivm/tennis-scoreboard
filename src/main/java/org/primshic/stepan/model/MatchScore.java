package org.primshic.stepan.model;

import lombok.Getter;

@Getter
public class MatchScore {
    public MatchScore() {
        this.player1Score = new Score();
        this.player2Score = new Score();
    }

    private Score player1Score;
    private Score player2Score;


}
