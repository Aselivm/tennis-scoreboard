package org.primshic.stepan;

import org.junit.Assert;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.score.AddPointButton;
import org.primshic.stepan.score.score.State;
import org.primshic.stepan.score.score_system.Point;
import org.primshic.stepan.score.score_system.point_types.RegularPoint;

public class Test {
    @org.junit.Test
    public void advantage() {
        Match match = new Match(1, 2);
        match.getMatchScore().getPlayer1Score().setPoint(new Point(RegularPoint.FORTY));
        match.getMatchScore().getPlayer2Score().setPoint(new Point(RegularPoint.FORTY));
        AddPointButton.addPoint(match, 1);
        Assert.assertEquals(0, match.getMatchScore().getPlayer1Score().getGame().getCounter());
    }

    @org.junit.Test
    public void gameIncrease() {
        Match match = new Match(1, 2);
        match.getMatchScore().getPlayer1Score().setPoint(new Point(RegularPoint.FORTY));
        AddPointButton.addPoint(match, 1);
        Assert.assertEquals(1, match.getMatchScore().getPlayer1Score().getGame().getCounter());
    }

    @org.junit.Test
    public void tieBreak() {
        Match match = new Match(1, 2);
        match.getMatchScore().getPlayer1Score().getGame().setCounter(6);
        match.getMatchScore().getPlayer2Score().getGame().setCounter(5);
        match.getMatchScore().getPlayer2Score().setPoint(new Point(RegularPoint.FORTY));
        AddPointButton.addPoint(match, 2);
        Assert.assertSame(match.getMatchScore().getState(), State.TIE_BREAK);
    }
}
