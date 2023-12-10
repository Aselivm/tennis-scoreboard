package org.primshic.stepan.service.score_system;

import lombok.Getter;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.point_types.RegularPoint;
import org.primshic.stepan.service.score_system.point_types.TieBreakPoint;

@Getter
public class Point {
    private RegularPoint regularPoint;
    private TieBreakPoint tieBreakPoint;

    private int counter;

    public Point() {
        ;
    }

    public Point increaseCounter() {
        if (super.getState() == State.REGULAR_GAME) {
            regularPoint = RegularPoint.values()[(regularPoint.ordinal() + 1) % RegularPoint.values().length];
            if (regularPoint == RegularPoint.AD) {
                state = State.ADVANTAGE;
            } else {
                return new Point(regularPoint.getCounter(), State.REGULAR_GAME);
            }
        } else if (state == State.TIE_BREAK) {
            tieBreakPoint = tieBreakPoint.increaseCounter();
            return new Point(tieBreakPoint.getCounter(), State.TIE_BREAK);
        }

    }


}
