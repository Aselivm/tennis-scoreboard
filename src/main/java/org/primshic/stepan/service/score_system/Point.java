package org.primshic.stepan.service.score_system;

import lombok.Getter;
import lombok.Setter;
import org.primshic.stepan.service.score_system.point_types.RegularPoint;
import org.primshic.stepan.service.score_system.point_types.TieBreakPoint;

@Getter
@Setter
public class Point {
    private RegularPoint regularPoint;
    private TieBreakPoint tieBreakPoint;

    private int counter;

    public Point() {
        regularPoint = RegularPoint.LOVE;
        tieBreakPoint = new TieBreakPoint();
    }

    public Point(TieBreakPoint tieBreakPoint) {
        this.counter = tieBreakPoint.getCounter();
        this.tieBreakPoint = tieBreakPoint;
    }

    public Point(RegularPoint regularPoint) {
        this.counter = regularPoint.getCounter();
        this.regularPoint = regularPoint;
    }
}
