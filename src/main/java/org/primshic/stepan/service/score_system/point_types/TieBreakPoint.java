package org.primshic.stepan.service.score_system.point_types;


import org.primshic.stepan.service.score_system.ScoreSystem;

import java.awt.*;

public class TieBreakPoint extends Point implements ScoreSystem<TieBreakPoint> {
    public int getCounter() {
        return counter;
    }

    private int counter;

    @Override
    public TieBreakPoint increaseCounter() {
        counter++;
        return this;
    }
}
