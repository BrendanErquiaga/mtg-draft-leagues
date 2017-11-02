package org.rd.draftleague.core.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.rd.draftleague.core.model.League;
import org.rd.draftleague.core.model.Player;

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

    public League create(League league) {
        return persist(league);
    }

    public Optional<League> update(Long id, League league) {
        Optional<League> existingLeague = findById(id);
        existingLeague.ifPresent(league1 -> league1.update(league));
        return existingLeague;
    }

    public void delete(League league) {
        currentSession().delete(league);
    }

    public Optional<League> addPlayer(Long leagueId, Player player) {
        Optional<League> exisitingLeague = findById(leagueId);
        exisitingLeague.ifPresent(league -> league.addPlayer(player));

        if(exisitingLeague.isPresent()){
            return update(leagueId, exisitingLeague.get());
        } else {
            return exisitingLeague;
        }
    }
}
