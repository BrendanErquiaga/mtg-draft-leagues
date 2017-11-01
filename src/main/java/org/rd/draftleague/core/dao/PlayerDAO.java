package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.Player;

import java.util.List;
import java.util.Optional;

public class PlayerDAO extends AbstractDAO<Player> {

    public PlayerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Player> findAll() {
        return list(namedQuery("org.rd.draftleague.core.model.Player.findAll"));
    }

    /**
     * @param name query parameter
     * @return list of players whose name or nickname contains the passed
     * parameter as a substring.
     */
    public List<Player> findByName(String name) {
        StringBuilder builder = new StringBuilder("%");
        builder.append(name).append("%");
        return list(
                namedQuery("org.rd.draftleague.core.model.Player.findByName")
                        .setParameter("name", builder.toString())
        );
    }

    public Optional<Player> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public Player create(Player player) {
        return persist(player);
    }

    public Optional<Player> update(Long id, Player player) {
        Optional<Player> existingPlayer = findById(id);
        existingPlayer.ifPresent(player1 -> player1.update(player));
        return existingPlayer;
    }

    public void delete(Player player) {
        currentSession().delete(player);
    }
}
