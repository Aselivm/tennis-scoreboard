package org.primshic.stepan.service.score_system;

import lombok.Getter;
import org.primshic.stepan.service.score_system.point_types.RegularPoint;
import org.primshic.stepan.service.score_system.point_types.State;
import org.primshic.stepan.service.score_system.point_types.TieBreakPoint;

@Getter
public class Point implements ScoreSystem<Point> {
    private State state;
    private RegularPoint regularPoint;
    private TieBreakPoint tieBreakPoint;

    private int counter;

    public Point(int counter, State state) {
        this.state = state;
        this.counter = counter;
    }

    public Point(State state) {
        this.state = state;
    }

    public Point() {
        this(0, State.REGULAR_GAME);
    }

    @Override
    public Point increaseCounter() {
        if (state == State.REGULAR_GAME) {
            regularPoint = RegularPoint.values()[(regularPoint.ordinal() + 1) % RegularPoint.values().length];
            if (regularPoint == RegularPoint.AD) {
                return new Point(State.ADVANTAGE);
            } else {
                return new Point(regularPoint.getCounter(), State.REGULAR_GAME);
            }
        } else if (state == State.TIE_BREAK) {
            tieBreakPoint = tieBreakPoint.increaseCounter();
            return new Point(tieBreakPoint.getCounter(), State.TIE_BREAK);
        }

    }


}
