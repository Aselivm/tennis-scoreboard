package org.primshic.stepan.score.score_handler_chain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreHandlerChainBuilder {
    public static ScoreHandler buildChain() {
        ScoreHandler equalizeHandler = new EqualizeHandler();
        ScoreHandler increasePointHandler = new IncreasePointHandler();
        ScoreHandler increaseGameHandler = new IncreaseGameHandler();
        ScoreHandler increaseSetHandler = new IncreaseSetHandler();
        ScoreHandler matchEndHandler = new MatchEndHandler();

        equalizeHandler.setNextHandler(increasePointHandler);
        increasePointHandler.setNextHandler(increaseGameHandler);
        increaseGameHandler.setNextHandler(increaseSetHandler);
        increaseSetHandler.setNextHandler(matchEndHandler);

        return equalizeHandler;
    }
}
