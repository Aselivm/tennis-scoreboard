package org.primshic.stepan.service;

import org.primshic.stepan.model.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private static final Map<UUID, Match> ongoingMatches = new HashMap<>();

    public static void addMatch(UUID uuid, Match match) {
        //todo проверки всякие?
        ongoingMatches.put(uuid, match);
    }
    //TODO Коллекция с онгоинг матчами в которую могут обращаться несколько потоков. Потокобезопасное взаимодействие
}
