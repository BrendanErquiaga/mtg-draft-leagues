package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.Card;
import org.rd.draftleague.core.model.CardList;

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

    public Card create(Card card) {
        return persist(card);
    }

    public Optional<Card> update(Long id, Card card) {
        Optional<Card> existingCard = findById(id);
        existingCard.ifPresent(card1 -> card1.update(card));
        return existingCard;
    }

    public void delete(Card card) {
        currentSession().delete(card);
    }
}
