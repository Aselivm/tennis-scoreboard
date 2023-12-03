package org.primshic.stepan.model.score;

import lombok.ToString;

@ToString
public enum Point {
    LOVE(0), FIFTEEN(15), THIRTY(30), FORTY(40);

    Point(int points) {
        this.counter = points;
    }

    private final int counter;

    public int getCounter() {
        return counter;
    }

    public Point addPoint() {
        Point[] values = Point.values();
        int nextIndex = (this.ordinal() + 1) % values.length;
        return values[nextIndex];
    }//todo тестирование
}
