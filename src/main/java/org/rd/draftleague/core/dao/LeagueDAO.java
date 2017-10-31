package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.League;

import java.util.List;
import java.util.Optional;

public class LeagueDAO extends AbstractDAO<League> {

    public LeagueDAO(SessionFactory sessionFactory) { super(sessionFactory); }

    public Optional<List<League>> findAll() {
        return Optional.ofNullable(list(query("select e from League e")));
    }

    public Optional<League> findById(long id) {
        return Optional.ofNullable(get(id));
    }
}
