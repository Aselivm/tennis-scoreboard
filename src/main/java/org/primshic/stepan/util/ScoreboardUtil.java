package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.model.Match;
import org.primshic.stepan.service.FinishedMatchesPersistenceService;
import org.primshic.stepan.service.score.Score;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScoreboardUtil {
    private static final FinishedMatchesPersistenceService finishedMatchesPersistenceService =
            new FinishedMatchesPersistenceService();

    //todo вау, как же я заебался рефакторить. refactoring needed
    public static void addPoint(Match match, int playerId) {
        Score playerScore;
        Score opponentScore;

        if (match.getPlayer1_id() == playerId) {
            playerScore = match.getMatchScore().getPlayer1Score();
            opponentScore = match.getMatchScore().getPlayer2Score();
        } else if (match.getPlayer2_id() == playerId) {
            playerScore = match.getMatchScore().getPlayer2Score();
            opponentScore = match.getMatchScore().getPlayer1Score();
        } else {
            //todo throw exception or handle the case when playerId doesn't match player1_id or player2_id
            return;
        }

        match.getMatchScore().addPoint(playerScore);

        //todo check if game finished
        if (playerScore.getSet().getCounter() == 2) {
            finishedMatchesPersistenceService.persist(match, playerId);
            //todo надо где-то зарендерить страничку
        }

    }

}
