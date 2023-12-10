package org.primshic.stepan.service.score;

import lombok.Getter;
import lombok.Setter;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.Set;

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

    public void pointReset() {
        this.point = new Point();
    }

    public void gameReset() {
        this.game = new Game();
    }

    public MatchScore getMatchScore() {
        return matchScore;
    }
}
