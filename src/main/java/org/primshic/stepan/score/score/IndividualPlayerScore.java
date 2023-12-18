package org.primshic.stepan.score.score;

import lombok.Getter;
import lombok.Setter;
import org.primshic.stepan.score.score_system.Game;
import org.primshic.stepan.score.score_system.Point;
import org.primshic.stepan.score.score_system.Set;

@Getter
@Setter
public class IndividualPlayerScore {
    public IndividualPlayerScore(MatchScore matchScore) {
        this.matchScore = matchScore;
        this.point = new Point();
        this.game = new Game();
        this.set = new Set();
    }

    private MatchScore matchScore;
    private Set set;
    private Game game;
    private Point point;

    public MatchScore getMatchScore() {
        return matchScore;
    }
}
