package org.primshic.stepan.service.score_system;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Set {
    private int counter;

    public Set increaseCounter() {
        counter++;
        return this;
    }
}
