package org.primshic.stepan.model;

import lombok.Getter;
import org.primshic.stepan.model.score.Game;
import org.primshic.stepan.model.score.Point;
import org.primshic.stepan.model.score.Set;

@Getter
public class Score {
    public Score() {
        this.point = Point.LOVE;
        this.game = new Game();
        this.set = new Set();
    }

    private Point point;
    private Game game;
    private Set set;

    public void addPoint() {
        this.point = point.increaseCounter();
    }//todo временная мера
}
