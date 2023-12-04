package org.primshic.stepan.model;

import lombok.Getter;
import lombok.Setter;
import org.primshic.stepan.model.score.Game;
import org.primshic.stepan.model.score.Point;
import org.primshic.stepan.model.score.Set;

@Getter
@Setter
public class Score {
    private Score() {
        this.point = Point.LOVE;
        this.game = new Game();
        this.set = new Set();
    }

    public static Score initScore() {
        return new Score();
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
