package org.primshic.stepan.service;

import jakarta.persistence.PersistenceException;
import org.primshic.stepan.dao.Players;

import java.util.Optional;

public class PlayersService {
    private final Players playersDAO = new Players();

    public Optional<org.primshic.stepan.model.Players> getById(int id) {
        return playersDAO.getById(id);
    }

    public Optional<org.primshic.stepan.model.Players> insertAndIgnoreDuplicate(String name) {
        org.primshic.stepan.model.Players player = new org.primshic.stepan.model.Players(name);
        try {
            return playersDAO.saveAndReturn(player);
        } catch (PersistenceException e) {
            return playersDAO.getByName(name);
        }
    }
}
