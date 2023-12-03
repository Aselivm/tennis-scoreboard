package org.primshic.stepan.model.score;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Set {
    private int counter;

    public void addSet() {
        counter++;
    }//todo тестирование
}
