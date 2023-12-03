package org.primshic.stepan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.primshic.stepan.service.PlayersService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayersUtil {
    private static final PlayersService playersService = new PlayersService();

    public static boolean isUnique(String name) {
        return playersService.getByName(name).isEmpty();
    }
}
