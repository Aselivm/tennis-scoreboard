package org.primshic.stepan.service;

import jakarta.persistence.PersistenceException;
import org.primshic.stepan.dao.PlayersDAO;
import org.primshic.stepan.entity.Players;

import java.util.Optional;

public class PlayersService {
    private final PlayersDAO playersDAO = new PlayersDAO();

    public Optional<Players> getById(int id) {
        return playersDAO.getById(id);
    }

    public Optional<Players> insertAndIgnoreDuplicate(String name) {
        Players player = new Players(name);
        try {
            return playersDAO.save(player);
        } catch (PersistenceException e) {
            return playersDAO.getByName(name);
        }
    }
}
