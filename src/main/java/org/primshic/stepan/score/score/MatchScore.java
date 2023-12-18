package org.primshic.stepan.score.score;

import lombok.Getter;

@Getter
public class MatchScore {
    public MatchScore() {
        this.state = State.REGULAR_GAME;
        this.player1Score = new IndividualPlayerScore(this);
        this.player2Score = new IndividualPlayerScore(this);
    }

    private State state;

    private final IndividualPlayerScore player1Score;

    private final IndividualPlayerScore player2Score;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

}
