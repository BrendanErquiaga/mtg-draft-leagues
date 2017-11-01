package org.rd.draftleague.core.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.rd.draftleague.core.dao.LeagueDAO;
import org.rd.draftleague.core.model.League;
import org.rd.draftleague.core.model.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/leagues")
@Produces(MediaType.APPLICATION_JSON)
public class LeaguesResource {
    private LeagueDAO leagueDAO;

    public LeaguesResource(LeagueDAO leagueDAO) { this.leagueDAO = leagueDAO; }

    @GET
    @UnitOfWork
    public Optional<List<League>> findAll() {
        return leagueDAO.findAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<League> findById(@PathParam("id")LongParam id) {
        return leagueDAO.findById(id.get());
    }

    @POST
    @UnitOfWork
    public League createLeague(League league) {
        return leagueDAO.create(league);
    }

    //TODO Figure out how to add individual items, players, drafts etc
}
