package org.primshic.stepan.model;

import lombok.Getter;

@Getter
public class Match {
    private final int player1_id;
    private final int player2_id;
    private final MatchScore matchScore;

    public Match(int player1_id, int player2_id) {
        matchScore = new MatchScore();
        this.player1_id = player1_id;
        this.player2_id = player2_id;
    }
}
