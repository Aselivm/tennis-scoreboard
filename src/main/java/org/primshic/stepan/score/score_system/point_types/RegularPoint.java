package org.primshic.stepan.score.score_system.point_types;

public enum RegularPoint {
    LOVE(0), FIFTEEN(15), THIRTY(30), FORTY(40), AD(99);

    RegularPoint(int points) {
        this.counter = points;
    }

    public int getCounter() {
        return counter;
    }

    private final int counter;

    public RegularPoint increaseCounter() {
        RegularPoint[] values = RegularPoint.values();
        int nextIndex = (this.ordinal() + 1) % values.length;
        return values[nextIndex];
    }
}
