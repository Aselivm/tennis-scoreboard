package org.primshic.stepan.score.score_system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Set {
    private int counter;

    public Set increaseCounter() {
        counter++;
        return this;
    }
}
