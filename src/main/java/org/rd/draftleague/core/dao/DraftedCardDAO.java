package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.DraftedCard;

import java.util.List;
import java.util.Optional;

public class DraftedCardDAO extends AbstractDAO<DraftedCard> {
    public DraftedCardDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<List<DraftedCard>> findAll() {
        return Optional.ofNullable(list(query("select d from DraftedCard d")));
    }

    public Optional<DraftedCard> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public DraftedCard create(DraftedCard draftedCard) {
        return persist(draftedCard);
    }

    public Optional<DraftedCard> update(Long id, DraftedCard draftedCard) {
        Optional<DraftedCard> existingCard = findById(id);
        existingCard.ifPresent(card1 -> card1.update(draftedCard));
        return existingCard;
    }

    public void delete(DraftedCard draftedCard) {
        currentSession().delete(draftedCard);
    }
}
