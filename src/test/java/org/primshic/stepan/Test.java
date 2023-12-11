package org.primshic.stepan;

import org.junit.Assert;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.score.State;
import org.primshic.stepan.service.score_system.Point;
import org.primshic.stepan.service.score_system.point_types.RegularPoint;
import org.primshic.stepan.util.ScoreboardUtil;

public class Test {
    @org.junit.Test
    public void advantage() {
        Match match = new Match(1, 2);
        match.getMatchScore().getPlayer1Score().setPoint(new Point(RegularPoint.FORTY));
        match.getMatchScore().getPlayer2Score().setPoint(new Point(RegularPoint.FORTY));
        ScoreboardUtil.addPoint(match, 1);
        Assert.assertEquals(0, match.getMatchScore().getPlayer1Score().getGame().getCounter());
    }

    @org.junit.Test
    public void gameIncrease() {
        Match match = new Match(1, 2);
        match.getMatchScore().getPlayer1Score().setPoint(new Point(RegularPoint.FORTY));
        ScoreboardUtil.addPoint(match, 1);
        Assert.assertEquals(1, match.getMatchScore().getPlayer1Score().getGame().getCounter());
    }

    @org.junit.Test
    public void tieBreak() {
        Match match = new Match(1, 2);
        match.getMatchScore().getPlayer1Score().getGame().setCounter(6);
        match.getMatchScore().getPlayer2Score().getGame().setCounter(5);
        match.getMatchScore().getPlayer2Score().setPoint(new Point(RegularPoint.FORTY));
        ScoreboardUtil.addPoint(match, 2);
        Assert.assertSame(match.getMatchScore().getState(), State.TIE_BREAK);
    }

    //todo Предлагаю студентам самостоятельно придумать тест кейсы для покрытия всех вариантов изменения счёта в матче,
    // особенно правила “больше-меньше” и тайбрейк. Набор тестов должен быть реализован с помощью JUnit 5.
}
