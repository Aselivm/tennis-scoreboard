package org.primshic.stepan.service.score_system;

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
    }//todo тестирование

    public void reset() {
        counter = 0;
    }
}
