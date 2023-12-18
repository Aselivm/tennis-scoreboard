package org.primshic.stepan.score.score_system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Game {
    private int counter;

    public Game increaseCounter() {
        counter++;
        return this;
    }

}
