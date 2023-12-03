package org.primshic.stepan.model.score;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Game implements ScoreSystem<Game> {
    private int counter;

    @Override
    public Game increaseCounter() {
        counter++;
        return this;
    }//todo тестирование
}
