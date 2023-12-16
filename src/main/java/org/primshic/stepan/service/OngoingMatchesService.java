package org.primshic.stepan.service;

import org.primshic.stepan.model.Match;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final Map<UUID, Match> ongoingMatches = new ConcurrentHashMap<>();

    public static void addMatch(UUID uuid, Match match) {
        ongoingMatches.put(uuid, match);
    }

    public static Match getMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public static void removeMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
    //TODO Коллекция с онгоинг матчами в которую могут обращаться несколько потоков. Потокобезопасное взаимодействие
}
