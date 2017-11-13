package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.Draft;
import org.rd.draftleague.core.model.League;

import java.util.List;
import java.util.Optional;

public class DraftDAO extends AbstractDAO<Draft> {

    public DraftDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<List<Draft>> findAll() {
        return Optional.ofNullable(list(query("select e from Draft e")));
    }

    public Optional<Draft> findById(long id) {
        return Optional.ofNullable(get(id));
    }

    public Draft create(Draft draft) {
        return persist(draft);
    }

    public Optional<Draft> update(Long id, Draft draft) {
        Optional<Draft> existingDraft = findById(id);
        existingDraft.ifPresent(draft1 -> draft1.updateDraft(draft));
        return existingDraft;
    }

    public void delete(Draft draft) {
        currentSession().delete(draft);
    }

    public Optional<Draft> joinLeague(Long draftId, League league) {
        Optional<Draft> existingDraft = findById(draftId);
        existingDraft.ifPresent(draft -> draft.joinLeauge(league));

        if(existingDraft.isPresent()) {
            return update(draftId, existingDraft.get());
        } else {
            return existingDraft;
        }
    }
}
