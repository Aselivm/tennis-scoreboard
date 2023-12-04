package org.primshic.stepan.model.score;

import lombok.Getter;

@Getter
public enum Point implements ScoreSystem<Point> {
    LOVE(0), FIFTEEN(15), THIRTY(30), FORTY(40), AD("AD");

    Point(int points) {
        this.counter = points;
    }

    Point(String ad) {
        this.ad = ad;
    }

    private String ad;

    private int counter;

    public Point reset() {
        return Point.LOVE;
    }

    public Object getView() {
        if (ad == null) return counter;
        else return ad;
    }

    @Override
    public Point increaseCounter() {
        Point[] values = Point.values();
        int nextIndex = (this.ordinal() + 1) % values.length;
        return values[nextIndex];
    }//todo тестирование
}
