package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.Card;

import java.util.List;
import java.util.Optional;

public class CardDAO extends AbstractDAO<Card> {
    public CardDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<List<Card>> findAll() {
        return Optional.ofNullable(list(query("select e from Card e")));
    }

    public Optional<Card> findById(long id) {
        return Optional.ofNullable(get(id));
    }
}
