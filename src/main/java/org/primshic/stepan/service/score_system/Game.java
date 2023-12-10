package org.primshic.stepan.service.score_system;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Game {
    private int counter;

    public Game increaseCounter() {
        counter++;
        return this;
    }//todo тестирование

    public void reset() {
        counter = 0;
    }
}
