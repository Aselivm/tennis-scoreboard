package org.primshic.stepan.model.score;

public enum Point {
    FIFTEEN(15), THIRTY(30), FORTY(40);

    Point(int points) {
        this.points = points;
    }

    private final int points;

    public int getPoints() {
        return points;
    }
}
