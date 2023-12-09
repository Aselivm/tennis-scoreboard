package org.primshic.stepan.service.score_system;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Set implements ScoreSystem<Set> {
    private int counter;

    @Override
    public Set increaseCounter() {
        counter++;
        return this;
    }
}
