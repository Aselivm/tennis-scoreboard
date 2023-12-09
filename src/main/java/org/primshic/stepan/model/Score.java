package org.primshic.stepan.model;

import lombok.Getter;
import lombok.Setter;
import org.primshic.stepan.service.score_system.Game;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.Set;

@Getter
@Setter
public class Score {
    public Score() {
        this.point = Point.LOVE;
        this.game = new Game();
        this.set = new Set();
    }

    private Set set;
    private Game game;
    private Point point;

    public void pointReset() {
        this.point = Point.LOVE;
    }

    public void gameReset() {
        this.game = new Game();
    }
}
