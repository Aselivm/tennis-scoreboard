package org.primshic.stepan.model;

public class Match {
    private final int player1_id;
    private final int player2_id;
    private MatchScore matchScore;

    public Match(int player1_id, int player2_id) {
        this.player1_id = player1_id;
        this.player2_id = player2_id;
    }
}
