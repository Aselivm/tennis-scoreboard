package org.primshic.stepan.model.score;

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
