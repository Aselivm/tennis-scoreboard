package org.primshic.stepan.model.score;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Game {
    private int counter;

    public void addGame() {
        counter++;
    }//todo тестирование
}
