package org.primshic.stepan.service;

import org.primshic.stepan.dao.PlayersDAO;
import org.primshic.stepan.entity.Players;

import java.util.Optional;

public class PlayersService {
    private final PlayersDAO playersDAO = new PlayersDAO();

    public Optional<Players> getByName(String name) {
        return playersDAO.getByName(name);
    }

    public Optional<Players> getById(int id) {
        return playersDAO.getById(id);
    }

    public Optional<Players> getEntity(String name) {
        Optional<Players> player = playersDAO.getByName(name);
        if (player.isEmpty()) {
            player = save(name);
        }
        return player;
    }

    public Optional<Players> save(String name) {
        Players player = new Players(name);
        return playersDAO.save(player);
    }
}
