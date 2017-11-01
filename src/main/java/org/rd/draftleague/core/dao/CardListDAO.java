package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.CardList;

import java.util.List;
import java.util.Optional;

public class CardListDAO extends AbstractDAO<CardList> {
    public CardListDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<List<CardList>> findAll() {
        return Optional.ofNullable(list(query("select e from CardList e")));
    }

    public Optional<CardList> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public CardList create(CardList cardList) {
        return persist(cardList);
    }

    public Optional<CardList> update(Long id, CardList cardList) {
        Optional<CardList> existingCardList = findById(id);
        existingCardList.ifPresent(cardList1 -> cardList1.update(cardList));
        return existingCardList;
    }

    public void delete(CardList cardList) {
        currentSession().delete(cardList);
    }
}
